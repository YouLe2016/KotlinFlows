package com.example.kotlinflows.part2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Part2Screen() {
    val viewModel = viewModel<MainViewModel2>()
    Text(text = "Part2Screen")
}