package com.example.navigationstatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CounterScreen() }
    }
}

@Composable
fun CounterScreen() {
    var count by remember { mutableStateOf(0) }
    Text("Current state value: $count")
}
