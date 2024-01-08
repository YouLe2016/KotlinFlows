package com.example.kotlinflows.part3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PartScreen3() {
    val viewModel = viewModel<MainViewModel3>()
    val count by viewModel.stateFlow.collectAsState(initial = StartValue)
    println(count)
    val count2 by viewModel.sharedFlow.collectAsState(initial = StartValue)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                viewModel.incrementCounterTimes(50)
            },
        ) {
            Text(
                text = "当前值：$count",
                fontSize = 30.sp,
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                viewModel.squareNumber(count)
            },
        ) {
            Text(
                text = "当前值：$count2",
                fontSize = 30.sp,
            )
        }
    }
}