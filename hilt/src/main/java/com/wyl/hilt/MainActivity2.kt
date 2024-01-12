package com.wyl.hilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.wyl.hilt.service.IDemoService
import com.wyl.hilt.ui.page.Greeting
import com.wyl.hilt.ui.theme.KotlinFlowsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {
    @Inject
    lateinit var service1: IDemoService

    @Inject
    lateinit var service2: IDemoService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFlowsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(
                        "$service1\n$service2",
                        modifier = Modifier.clickable {
                            finish()
                        }
                    )
                }
            }
        }
    }
}