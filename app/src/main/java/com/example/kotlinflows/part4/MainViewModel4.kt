package com.example.kotlinflows.part4

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

const val StartValue = 10
private const val EmitDelay = 1000L
private const val CollectDelay = 1000L

class MainViewModel4(
    private val dispatchers: DispatcherProvider
) : ViewModel() {
    val countDownFlow = flow {
        for (i in StartValue downTo 0) {
            println("TAG3, flow: $i")
            emit(i)
            delay(EmitDelay)
        }
    }.flowOn(dispatchers.main)

    init {
        collectFlow()
        collectFlow2()
    }

    private fun collectFlow() {
        viewModelScope.launch(dispatchers.main) {
            countDownFlow.collect {
                delay(CollectDelay)
                println("TAG, collectFlow: $it")
            }
        }
    }

    private fun collectFlow2() {
        viewModelScope.launch(dispatchers.main) {
            countDownFlow.collectLatest {
                delay(CollectDelay)
                println("TAG2, collectFlow2: $it")
            }
        }
    }
}