package com.example.kotlinflows.part3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

    /**
     * 都一样
     * _stateFlow.value++： 只收到最后一次
     * _stateFlow.emit：只收到最后一次
     * _sharedFlow.emit：只收到最后一次
     */
    val count by viewModel.stateFlow.collectAsState(initial = StartValue)
    val count2 by viewModel.sharedFlow.collectAsState(initial = StartValue)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "stateFlow：$count",
            fontSize = 24.sp,
        )
        Text(
            text = "sharedFlow：$count2",
            fontSize = 24.sp,
        )
        Button(
            onClick = {
                viewModel.stateFlowIncrementTimes1(50)
            },
        ) {
            Text(text = "stateFlowIncrementTimes1")
        }
        Button(
            onClick = {
                viewModel.stateFlowIncrementTimes2(50)
            },
        ) {
            Text(text = "stateFlowIncrementTimes2")
        }
        Button(
            onClick = {
                viewModel.sharedFlowIncrementTimes(50)
            },
        ) {
            Text(text = "sharedFlowIncrementTimes")
        }
    }
}