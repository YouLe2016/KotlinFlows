package com.example.kotlinflows

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kotlinflows.part1.MainViewModel
import com.example.kotlinflows.part3.MainViewModel3
import com.example.kotlinflows.part3.PartScreen3
import com.example.kotlinflows.ui.theme.KotlinFlowsTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel3 by viewModels()
    private val viewModel2: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        xmlLayout()
//        setContent {
//            KotlinFlowsTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
////                     Greeting()
//                    // Part2Screen()
////                     PartScreen3()
//                     // PartScreen5()
//                }
//            }
//        }
    }

    private fun xmlLayout() {
        val linearLayout = LinearLayout(this).apply {
            // 设置tvCounter大小占满屏幕，并且剧中
            layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            gravity = Gravity.CENTER
            orientation = LinearLayout.VERTICAL
        }

        val tvCounter = TextView(this).apply {
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        }
        // 24sp转化成px
        tvCounter.textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            14f,
            resources.displayMetrics
        )
        tvCounter.text = "StateFlow：0"
        collectLifecycleFlow(viewModel.stateFlow) {
            Log.d(TAG, "collect（stateFlow）: $it")
            tvCounter.text = "StateFlow：$it"
        }

        val tvCounter2 = TextView(this).apply {
            text = "SharedFlow：0"
            textSize = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                14f,
                resources.displayMetrics
            )
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                setMargins(
                    0, TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        10f,
                        resources.displayMetrics
                    ).roundToInt(), 0, 0
                )
            }
            collectLifecycleFlow(viewModel.sharedFlow) {
                Log.d(TAG, "collect（sharedFlow）: $it")
                text = "SharedFlow：$it"
            }
        }
        val button1 = Button(this).apply {
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            text = "stateFlowIncrementTimes1"
            isAllCaps = false
            setOnClickListener {
                Log.d(TAG, "onClick: ")
                viewModel.stateFlowIncrementTimes1(50)
            }
        }
        val button2 = Button(this).apply {
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            text = "stateFlowIncrementTimes2"
            isAllCaps = false
            setOnClickListener {
                Log.d(TAG, "onClick: ")
                viewModel.stateFlowIncrementTimes2(50)
            }
        }
        val button3 = Button(this).apply {
            layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            text = "sharedFlowIncrementTimes"
            isAllCaps = false
            setOnClickListener {
                Log.d(TAG, "onClick: ")
                viewModel.sharedFlowIncrementTimes(50)
            }
        }
        setContentView(
            linearLayout.apply {
                addView(tvCounter)
                addView(tvCounter2)

                addView(button1)
                addView(button2)
                addView(button3)
            }
        )
    }
}

/**
 * _stateFlow.value++： 50次全部收到
 * _stateFlow.emit：只收到最后一次
 * _sharedFlow.emit：50次全部收到
 */
fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    // 在xml中将如何收集flow的结果
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            Log.d(TAG, "repeatOnLifecycle: ")
            // 通常只接收最后状态
            flow.collectLatest(collect)
        }
    }
}

/**
 * 和上面没有区别啊
 * _stateFlow.value++： 50次全部收到
 * _stateFlow.emit：只收到最后一次
 * _sharedFlow.emit：50次全部收到
 */
fun <T> ComponentActivity.collectLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    // 在xml中将如何收集flow的结果
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            Log.d(TAG, "repeatOnLifecycle: ")
            flow.collect(collect)
        }
    }
}