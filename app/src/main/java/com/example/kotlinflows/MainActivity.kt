package com.example.kotlinflows

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kotlinflows.part3.MainViewModel3
import com.example.kotlinflows.part3.PartScreen3
import com.example.kotlinflows.part5.PartScreen5
import com.example.kotlinflows.ui.theme.KotlinFlowsTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.ObjectInputStream
import kotlin.math.roundToInt
import kotlin.system.measureTimeMillis

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel3 by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


         xmlLayout()
//        setContent {
//            KotlinFlowsTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    // Greeting()
//                    // Part2Screen()
//                     PartScreen3()
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
        tvCounter.setOnClickListener {
            Log.d(TAG, "onClick: ")
            viewModel.incrementCounterTimes(50)
        }
        tvCounter.text = "当前值1321312"
        collectLifecycleFlow(viewModel.stateFlow) {
            Log.d(TAG, "collect: $it")
//            tvCounter.text = "当前值：$it"
        }

        val tvCounter2 = TextView(this).apply {
            text = "当前值：0"
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
            setOnClickListener {
                Log.d(TAG, "onClick: ")
                viewModel.squareNumber(viewModel.stateFlow.value)
            }
            collectLifecycleFlow(viewModel.sharedFlow) {
                Log.d(TAG, "collect: $it")
                text = "当前值：$it"
            }
        }
        setContentView(
            linearLayout.apply {
                addView(tvCounter)
                addView(tvCounter2)
            }
        )
    }
}

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

fun <T> ComponentActivity.collectLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    // 在xml中将如何收集flow的结果
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            Log.d(TAG, "repeatOnLifecycle: ")
            flow.collect(collect)
        }
    }
}