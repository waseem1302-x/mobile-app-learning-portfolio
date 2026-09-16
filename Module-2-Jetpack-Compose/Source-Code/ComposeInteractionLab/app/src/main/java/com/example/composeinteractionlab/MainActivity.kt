package com.example.composeinteractionlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeinteractionlab.ui.theme.PortfolioTheme
import java.text.NumberFormat
import kotlin.math.ceil
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PortfolioTheme { ComposeInteractionLabApp() } }
    }
}

fun calculateTip(amount: Double, percentage: Int, roundUp: Boolean): Double {
    if (amount <= 0.0 || percentage < 0) return 0.0
    val tip = amount * percentage / 100
    return if (roundUp) ceil(tip) else tip
}

fun parseAmountInput(input: String): Double? {
    val value = input.trim()
    if (value.isEmpty() || value.count { it == '.' || it == ',' } > 1) return null
    if (value.any { !it.isDigit() && it != '.' && it != ',' }) return null
    return value.replace(',', '.').toDoubleOrNull()
}

private fun isPotentialAmountInput(input: String): Boolean =
    input.all { it.isDigit() || it == '.' || it == ',' } &&
        input.count { it == '.' || it == ',' } <= 1

@Composable
fun ComposeInteractionLabApp(modifier: Modifier = Modifier) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Compose Interaction Lab",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Two small exercises demonstrating event handling, input and observable UI state.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            DiceRollerCard()
            TipCalculatorCard()
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun DiceRollerCard() {
    var diceValue by rememberSaveable { mutableIntStateOf(1) }
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Dice Roller", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            Text(
                text = diceValue.toString(),
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Button(onClick = { diceValue = Random.nextInt(from = 1, until = 7) }) {
                Text("Roll dice")
            }
        }
    }
}

@Composable
private fun TipCalculatorCard() {
    var amountInput by rememberSaveable { mutableStateOf("") }
    var percentage by rememberSaveable { mutableIntStateOf(15) }
    var roundUp by rememberSaveable { mutableStateOf(false) }
    val amount = parseAmountInput(amountInput) ?: 0.0
    val formattedTip = NumberFormat.getCurrencyInstance().format(calculateTip(amount, percentage, roundUp))

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text("Tip Calculator", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            OutlinedTextField(
                value = amountInput,
                onValueChange = { value -> if (isPotentialAmountInput(value)) amountInput = value },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Bill amount") },
                prefix = { Text(NumberFormat.getCurrencyInstance().currency?.symbol.orEmpty()) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            )
            Text("Tip percentage: $percentage%", fontWeight = FontWeight.Medium)
            Slider(
                value = percentage.toFloat(),
                onValueChange = { percentage = it.toInt() },
                valueRange = 0f..30f,
                steps = 5,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Round up tip")
                Switch(checked = roundUp, onCheckedChange = { roundUp = it })
            }
            Text(
                text = "Tip: $formattedTip",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
