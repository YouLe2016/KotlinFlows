package com.example.kotlinflows.part3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

const val StartValue = 0

class MainViewModel3 : ViewModel() {
    // StateFlow
    private val _stateFlow = MutableStateFlow(StartValue)
    val stateFlow = _stateFlow.asStateFlow()

    // SharedFlow
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow

    fun squareNumber(number: Int) {
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }

    fun incrementCounter() {
        _stateFlow.value++
    }

    fun incrementCounterTimes(times: Int) {
            thread {
                for (i in 1..times) {
//                delay(500L)
                    if (i % 2 != 0) {
                        incrementCounter()
                    } else {
                        _stateFlow.value = _stateFlow.value
                    }
                }
            }.start()
//        viewModelScope.launch {
//            for (i in 1..times) {
//                incrementCounter()
////                delay(500L)
//            }
//        }
    }
}
