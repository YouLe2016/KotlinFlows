package com.example.kotlinflows.part1

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinflows.ui.theme.KotlinFlowsTheme

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val viewModel = viewModel<MainViewModel>()
    val count by viewModel.countDownFlow.collectAsState(initial = StartValue)
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            text = count.toString(),
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinFlowsTheme {
        Greeting()
    }
}