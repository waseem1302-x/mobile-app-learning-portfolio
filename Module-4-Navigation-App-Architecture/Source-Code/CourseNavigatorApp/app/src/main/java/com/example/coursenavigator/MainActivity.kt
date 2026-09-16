package com.example.coursenavigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coursenavigator.ui.theme.CourseNavigatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { CourseNavigatorTheme { CourseNavigatorApp() } }
    }
}

private object Routes {
    const val Home = "home"
    const val Topics = "topics"
    const val Detail = "detail/{topicId}"
    fun detail(topicId: Int) = "detail/$topicId"
}

fun usesExpandedLayout(widthDp: Float): Boolean = widthDp >= 700f

@Composable
fun CourseNavigatorApp(courseViewModel: CourseViewModel = viewModel()) {
    val navController = rememberNavController()
    val uiState by courseViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = Routes.Home) {
        composable(Routes.Home) {
            HomeScreen(onStart = { navController.navigate(Routes.Topics) })
        }
        composable(Routes.Topics) {
            TopicsScreen(
                selectedTopicId = uiState.selectedTopicId,
                onSelect = courseViewModel::selectTopic,
                onOpenDetail = { topicId -> navController.navigate(Routes.detail(topicId)) },
            )
        }
        composable(
            route = Routes.Detail,
            arguments = listOf(navArgument("topicId") { type = NavType.IntType }),
        ) { entry ->
            val topicId = entry.arguments?.getInt("topicId") ?: return@composable
            CourseCatalog.find(topicId)?.let { TopicDetail(it) }
        }
    }
}

@Composable
private fun HomeScreen(onStart: () -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Course Navigator", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
            Text(
                "Explore the architecture, navigation and adaptive-layout skills practised in Unit 4.",
                modifier = Modifier.padding(vertical = 16.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Button(onClick = onStart) { Text("Start learning") }
        }
    }
}

@Composable
private fun TopicsScreen(
    selectedTopicId: Int?,
    onSelect: (Int) -> Unit,
    onOpenDetail: (Int) -> Unit,
) {
    Scaffold { innerPadding ->
        BoxWithConstraints(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            val expanded = usesExpandedLayout(maxWidth.value)
            if (expanded) {
                Row(modifier = Modifier.fillMaxSize().padding(20.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    TopicList(onSelect = onSelect, modifier = Modifier.weight(1f))
                    val selected = selectedTopicId?.let(CourseCatalog::find) ?: CourseCatalog.topics.first()
                    TopicDetail(selected, modifier = Modifier.weight(1.2f))
                }
            } else {
                TopicList(
                    onSelect = { topicId ->
                        onSelect(topicId)
                        onOpenDetail(topicId)
                    },
                    modifier = Modifier.fillMaxSize().padding(20.dp),
                )
            }
        }
    }
}

@Composable
private fun TopicList(onSelect: (Int) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Text("Choose a topic", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Tap a card to open its learning outcomes.", modifier = Modifier.padding(top = 4.dp, bottom = 10.dp))
        }
        items(CourseCatalog.topics, key = { it.id }) { topic ->
            Card(modifier = Modifier.fillMaxWidth().clickable { onSelect(topic.id) }) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(topic.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(topic.summary, modifier = Modifier.padding(top = 6.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
private fun TopicDetail(topic: CourseTopic, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(topic.title, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text(topic.summary, style = MaterialTheme.typography.bodyLarge)
        Text("Learning outcomes", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
        topic.outcomes.forEach { outcome -> Text("• $outcome") }
    }
}
