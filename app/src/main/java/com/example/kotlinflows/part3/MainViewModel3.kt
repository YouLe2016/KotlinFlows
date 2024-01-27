package com.example.kotlinflows.part3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val StartValue = 0

class MainViewModel3 : ViewModel() {
    // StateFlow
    private val _stateFlow = MutableStateFlow(StartValue)
    val stateFlow = _stateFlow.asStateFlow()

    // SharedFlow
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow

    fun stateFlowIncrementTimes2(times: Int) {
        viewModelScope.launch {
            for (i in 1..times) {
                _stateFlow.emit(++_stateFlow.value)
            }
        }
    }

    fun stateFlowIncrementTimes1(times: Int) {
        for (i in 1..times) {
            _stateFlow.value++
        }
    }

    private var _sharedFlowCount = 0

    fun sharedFlowIncrementTimes(times: Int) {
        viewModelScope.launch {
            for (i in 1..times) {
                _sharedFlow.emit(++_sharedFlowCount)
            }
        }
    }
}
