package com.example.kotlinflows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.concurrent.thread

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() = runBlocking {
//        assertEquals(4, 2 + 2)

        (1..50).asFlow().collect {
            println("it: $it")
        }

        Unit
    }

    @Test
    fun changeNumbers() {
        var a = 1
        var b = 2
        println("a: $a, b: $b")
        a.let {
            a = b
            b = it
        }
        println("a: $a, b: $b")
    }

    @Test
    fun changeNumbers2() {
        var a = 1
        var b = 2
        println("a: $a, b: $b")
        val temp = a
        a = b
        b = temp
        println("a: $a, b: $b")
    }

    @Test
    fun sleep() {
        val thread = thread {
            var a = 1
            while (true) {
                println(a++)
                Thread.sleep(1000L)
            }
        }
        Thread.sleep(5000L)
        println("interrupt")
        thread.interrupt()
        thread(isDaemon = true) {
            while (true) {
                println("还在执行中")
                Thread.sleep(10_000L)
            }
        }
    }

}