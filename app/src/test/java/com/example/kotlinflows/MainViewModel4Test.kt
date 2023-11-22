package com.example.kotlinflows

import app.cash.turbine.test
import com.example.kotlinflows.part4.MainViewModel4
import com.example.kotlinflows.part4.StartValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel4Test {
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var viewModel: MainViewModel4

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        viewModel = MainViewModel4(testDispatchers)
    }

    @Test
    fun testCountDownFlow() = runBlocking {
        viewModel.countDownFlow.test {
            for (i in StartValue downTo 0) {
                // 这里没有advanceTimeBy方法
                testDispatchers.testDispatcher.scheduler.apply {
                    advanceTimeBy(1000L)
                    runCurrent()
                }
                val emissions = awaitItem()
                println("emissions: $emissions")
                assert(emissions == i)

            }
            cancelAndConsumeRemainingEvents()
        }
    }
}