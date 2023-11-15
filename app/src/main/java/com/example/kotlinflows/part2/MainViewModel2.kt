package com.example.kotlinflows.part2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel2"

private const val StartValue = 5
private const val EmitDelay = 1000L

class MainViewModel2 : ViewModel() {
    private val countDownFlow = flow {
        for (i in StartValue downTo 0) {
            Log.d(TAG, "flow: $i")
            emit(i)
//            delay(EmitDelay)
        }
    }

    init {
        viewModelScope.launch {
            // collectFlow()
            // count()
            // reduce()

            //  flatMapConcat()
            //  flatMapMerge()

           //  buffer()
            conflate()
        }
//        collectFlow2()
    }

    /*
    中间会隔过去一些
     */
    private suspend fun conflate() {
        flow {
            val times = 4
            for (i in 1..times) {
                if (i == 2) {
                    delay(3000L)
                } else {
                    delay(1000L)
                }
                Log.d(TAG, "flow: 步骤$i")
                emit("步骤$i")
            }
        }.onEach {
            Log.d(TAG, "onEach: $it")
        }.conflate().collect {
            Log.d(TAG, "collect: $it start")
            delay(3000L)
            Log.d(TAG, "collect: $it end")
        }
    }

    /*
    带缓冲，不过是顺序的
    输出结果：
2023-11-15 11:13:25.451 15533-15533 MainViewModel2          com.example.kotlinflows              D  flow: 步骤1
2023-11-15 11:13:25.451 15533-15533 MainViewModel2          com.example.kotlinflows              D  onEach: 步骤1
2023-11-15 11:13:25.452 15533-15533 MainViewModel2          com.example.kotlinflows              D  collect: 步骤1 start
2023-11-15 11:13:26.453 15533-15533 MainViewModel2          com.example.kotlinflows              D  flow: 步骤2
2023-11-15 11:13:26.453 15533-15533 MainViewModel2          com.example.kotlinflows              D  onEach: 步骤2
2023-11-15 11:13:27.454 15533-15533 MainViewModel2          com.example.kotlinflows              D  flow: 步骤3
2023-11-15 11:13:27.455 15533-15533 MainViewModel2          com.example.kotlinflows              D  onEach: 步骤3
2023-11-15 11:13:28.453 15533-15533 MainViewModel2          com.example.kotlinflows              D  collect: 步骤1 end
2023-11-15 11:13:28.453 15533-15533 MainViewModel2          com.example.kotlinflows              D  collect: 步骤2 start
2023-11-15 11:13:29.457 15533-15533 MainViewModel2          com.example.kotlinflows              D  flow: 步骤4
2023-11-15 11:13:29.457 15533-15533 MainViewModel2          com.example.kotlinflows              D  onEach: 步骤4
2023-11-15 11:13:31.456 15533-15533 MainViewModel2          com.example.kotlinflows              D  collect: 步骤2 end
2023-11-15 11:13:31.456 15533-15533 MainViewModel2          com.example.kotlinflows              D  collect: 步骤3 start
2023-11-15 11:13:34.460 15533-15533 MainViewModel2          com.example.kotlinflows              D  collect: 步骤3 end
2023-11-15 11:13:34.461 15533-15533 MainViewModel2          com.example.kotlinflows              D  collect: 步骤4 start
2023-11-15 11:13:37.466 15533-15533 MainViewModel2          com.example.kotlinflows              D  collect: 步骤4 end
     */
    private suspend fun buffer() {
        flow {
            val times = 3
            for (i in 1..times) {
                delay(1000L)
                Log.d(TAG, "flow: 步骤$i")
                emit("步骤$i")
            }
            delay(2000L)
            Log.d(TAG, "flow: 步骤${times + 1}")
            emit("步骤${times + 1}")
        }.onEach {
            Log.d(TAG, "onEach: $it")
        }.buffer().collect {
            Log.d(TAG, "collect: $it start")
            delay(3000L)
            Log.d(TAG, "collect: $it end")
        }
    }

    /*
   一起发出的，并发map的结果，会按顺序输出。
   使用案例：比如先从本次磁盘中取数据，再从网络中取数据
   输出结果：
2023-11-15 10:35:21.482 12092-12092 MainViewModel2          com.example.kotlinflows              D  flow: 5
2023-11-15 10:35:21.484 12092-12092 MainViewModel2          com.example.kotlinflows              D  flow: 4
2023-11-15 10:35:21.485 12092-12092 MainViewModel2          com.example.kotlinflows              D  flow: 3
2023-11-15 10:35:21.486 12092-12092 MainViewModel2          com.example.kotlinflows              D  flow: 2
2023-11-15 10:35:21.486 12092-12092 MainViewModel2          com.example.kotlinflows              D  flow: 1
2023-11-15 10:35:21.487 12092-12092 MainViewModel2          com.example.kotlinflows              D  flow: 0
2023-11-15 10:35:21.489 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: start 5
2023-11-15 10:35:21.490 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: start 4
2023-11-15 10:35:21.491 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: start 3
2023-11-15 10:35:21.492 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: start 2
2023-11-15 10:35:21.492 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: start 1
2023-11-15 10:35:21.493 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: start 0
2023-11-15 10:35:21.494 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 5 1
2023-11-15 10:35:21.494 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 4 1
2023-11-15 10:35:21.494 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 3 1
2023-11-15 10:35:21.494 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 2 1
2023-11-15 10:35:21.495 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 1 1
2023-11-15 10:35:21.495 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 0 1
2023-11-15 10:35:22.491 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 5 2
2023-11-15 10:35:22.492 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: end 5
2023-11-15 10:35:22.493 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 4 2
2023-11-15 10:35:22.494 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: end 4
2023-11-15 10:35:22.496 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 3 2
2023-11-15 10:35:22.496 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: end 3
2023-11-15 10:35:22.498 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 2 2
2023-11-15 10:35:22.498 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: end 2
2023-11-15 10:35:22.500 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 1 2
2023-11-15 10:35:22.500 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: end 1
2023-11-15 10:35:22.502 12092-12092 MainViewModel2          com.example.kotlinflows              D  collect: 0 2
2023-11-15 10:35:22.502 12092-12092 MainViewModel2          com.example.kotlinflows              D  flatMapMerge: end 0
    */
    @OptIn(FlowPreview::class)
    private suspend fun flatMapMerge() {
        (5 downTo 0).asFlow().flatMapMerge {
            flow {
                Log.d(TAG, "flatMapMerge: start $it")
                emit("$it 1")
                delay(EmitDelay)
                emit("$it 2")
                Log.d(TAG, "flatMapMerge: end $it")
            }
        }.collect {
            Log.d(TAG, "collect: $it")
        }
    }

    /*
    一个个发出的，顺序map结果，按顺序输出。
    输出结果：
2023-11-15 10:45:51.647 13464-13464 MainViewModel2          com.example.kotlinflows              D  flow: 5
2023-11-15 10:45:51.649 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: start 5
2023-11-15 10:45:51.649 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 5 1
2023-11-15 10:45:52.651 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 5 2
2023-11-15 10:45:52.651 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: end 5
2023-11-15 10:45:52.651 13464-13464 MainViewModel2          com.example.kotlinflows              D  flow: 4
2023-11-15 10:45:52.651 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: start 4
2023-11-15 10:45:52.651 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 4 1
2023-11-15 10:45:53.655 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 4 2
2023-11-15 10:45:53.655 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: end 4
2023-11-15 10:45:53.656 13464-13464 MainViewModel2          com.example.kotlinflows              D  flow: 3
2023-11-15 10:45:53.657 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: start 3
2023-11-15 10:45:53.658 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 3 1
2023-11-15 10:45:54.663 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 3 2
2023-11-15 10:45:54.663 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: end 3
2023-11-15 10:45:54.664 13464-13464 MainViewModel2          com.example.kotlinflows              D  flow: 2
2023-11-15 10:45:54.665 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: start 2
2023-11-15 10:45:54.666 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 2 1
2023-11-15 10:45:55.669 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 2 2
2023-11-15 10:45:55.670 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: end 2
2023-11-15 10:45:55.671 13464-13464 MainViewModel2          com.example.kotlinflows              D  flow: 1
2023-11-15 10:45:55.672 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: start 1
2023-11-15 10:45:55.673 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 1 1
2023-11-15 10:45:56.676 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 1 2
2023-11-15 10:45:56.677 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: end 1
2023-11-15 10:45:56.678 13464-13464 MainViewModel2          com.example.kotlinflows              D  flow: 0
2023-11-15 10:45:56.679 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: start 0
2023-11-15 10:45:56.680 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 0 1
2023-11-15 10:45:57.683 13464-13464 MainViewModel2          com.example.kotlinflows              D  collect: 0 2
2023-11-15 10:45:57.684 13464-13464 MainViewModel2          com.example.kotlinflows              D  flatMapConcat: end 0
     */
    @OptIn(FlowPreview::class)
    private suspend fun flatMapConcat() {
        (5 downTo 0).asFlow().flatMapConcat {
            flow {
                Log.d(TAG, "flatMapConcat: start $it")
                emit("$it 1")
                delay(EmitDelay)
                emit("$it 2")
                Log.d(TAG, "flatMapConcat: end $it")
            }
        }.collect {
            Log.d(TAG, "collect: $it")
        }
    }

    private suspend fun count() {
        val count = countDownFlow.count {
            it > 5 && it % 2 == 0
        }
        Log.d(TAG, "collectFlow3: $count")
    }

    private suspend fun reduce() {
//        val sum = countDownFlow.reduce { accumulator, value ->
//            accumulator + value
//        } // 55

        // 和 reduce 相比，可以设置初始值
        val sum = countDownFlow.fold(100) { accumulator, value ->
            accumulator + value
        } // 155
        Log.d(TAG, "collectFlow3: $sum")
    }

    /**
     * 一种简写方式
     */
    private fun collectFlow2() {
        countDownFlow.onEach {
            Log.d(TAG, "collectFlow2.onEach: $it")
        }.launchIn(viewModelScope)
    }

    private suspend fun collectFlow() {
        countDownFlow.filter { it % 2 == 0 }
            .onEach {
                Log.d(TAG, "collectFlow.onEach: $it")
            }
            .map { it * it }
            .count { it > 50 }
    }
}