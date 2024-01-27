package com.example.kotlinflows.part1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

const val StartValue = 5
private const val EmitDelay = 1000L
private const val CollectDelay = 2000L

private const val TAG = "MainViewModel_1"
private const val TAG2 = "MainViewModel_2"
private const val TAG3 = "MainViewModel_3"

class MainViewModel : ViewModel() {
    /**
     * 没有 collect() 的话，flow()的block不会执行
     */
    val countDownFlow = flow {
        for (i in 500 downTo 1) {
            Log.d(TAG3, "flow: $i")
            emit(i)
            delay(100)
        }
    }

    init {
//        collectFlow()
//        collectLatestFlow()
    }


    /**
    输出结果：
    flow1(0)
    collect: flow1(0)
    collect: flow1(0)
    flow2(0)
    collect: flow2(0)
    collect: flow2(0)
    ------------------------------------
    flow1(1)
    耗时操作。。
    collect: flow1(1)
    flow2(1)
    耗时操作。。
    collect: flow2(1)
     */
    private fun collectFlow() {
        viewModelScope.launch {
            var flag = 0
            flow {
                val msg = "flow1(${flag})"
                val msg2 = "flow2(${flag})"
                Log.d(TAG, msg)
                emit(msg)
                delay(EmitDelay)
                Log.d(TAG, msg2)
                emit(msg2)
            }.collect {
                Log.d(TAG, "collect: $it")
                delay(CollectDelay)
                Log.d(TAG, "collect: $it")
            }
            Log.d(TAG, "------------------------------------")
            flag++
            flow {
                val msg = "flow1(${flag})"
                val msg2 = "flow2(${flag})"
                Log.d(TAG, msg)
                emit(msg)
                Log.d(TAG, msg2)
                emit(msg2)
            }.collect {
                Log.d(TAG, "耗时操作。。")
                Thread.sleep(2000L)
                Log.d(TAG, "collect: $it")
            }
        }
    }

    /**
    当 CollectDelay > EmitDelay时，只输出最后结果。
    当 CollectDelay <= EmitDelay时，输出结果和 collectFlow() 一样

    输出结果：
    flow1(0)
    collect: flow1(0)
    // 这里少了一次，collectLatest 的效果
    flow2(0)
    collect: flow2(0)
    collect: flow2(0)
    ------------------------------------
    flow1(1)
    耗时操作。。
    collect: flow1(1)
    flow2(1)
    耗时操作。。
    collect: flow2(1)
     */
    private fun collectLatestFlow() {
        viewModelScope.launch {
            var flag = 0
            flow {
                val msg = "flow1(${flag})"
                Log.d(TAG, msg)
                emit(msg)
                delay(EmitDelay)
                val msg2 = "flow2(${flag})"
                Log.d(TAG, msg2)
                emit(msg2)
            }.collectLatest {
                Log.d(TAG, "collect: $it")
                delay(CollectDelay)
                Log.d(TAG, "collect: $it")
            }
            Log.d(TAG, "------------------------------------")
            flag++
            flow {
                val msg = "flow1(${flag})"
                Log.d(TAG, msg)
                emit(msg)
                val msg2 = "flow2(${flag})"
                Log.d(TAG, msg2)
                emit(msg2)
            }.collectLatest {
                Log.d(TAG, "耗时操作。。")
                Thread.sleep(2000L)
                Log.d(TAG, "collect: $it")
            }
        }
    }
}