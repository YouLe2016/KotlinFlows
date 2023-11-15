package com.example.kotlinflows.part1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

const val StartValue = 10
private const val EmitDelay = 1000L
private const val CollectDelay = 1000L

private const val TAG = "MainViewModel_1"
private const val TAG2 = "MainViewModel_2"
private const val TAG3 = "MainViewModel_3"

class MainViewModel : ViewModel() {
    /**
     * 没有 collect() 的话，flow()的block不会执行
     */
    val countDownFlow = flow {
        for (i in StartValue downTo 0) {
            Log.d(TAG3, "flow: $i")
            emit(i)
            delay(EmitDelay)
        }
    }

    init {
        collectFlow()
        collectFlow2()
    }

    /**
    输出：
    collectFlow.onEach: 5
    collectFlow.onEach: 4
    collectFlow.onEach: 3
    collectFlow.onEach: 2
    collectFlow.onEach: 1
    collectFlow.onEach: 0
     */
    private fun collectFlow() {
        viewModelScope.launch {
            countDownFlow.collect {
                delay(CollectDelay)
                Log.d(TAG, "collectFlow: $it")
            }
        }
    }

    /**
    当 CollectDelay > EmitDelay时，只输出最后结果。
    当 CollectDelay <= EmitDelay时，输出结果和 collectFlow() 一样
     */
    private fun collectFlow2() {
        viewModelScope.launch {
            countDownFlow.collectLatest {
                delay(CollectDelay)
                Log.d(TAG2, "collectFlow2: $it")
            }
        }
    }
}