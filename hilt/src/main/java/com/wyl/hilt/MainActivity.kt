package com.wyl.hilt

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.wyl.hilt.service.DemoService
import com.wyl.hilt.service.Fruit
import com.wyl.hilt.service.IDemoService
import com.wyl.hilt.ui.page.Greeting
import com.wyl.hilt.ui.theme.KotlinFlowsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var demoService: DemoService

    @Inject
    lateinit var service1: IDemoService

    @Inject
    lateinit var fruit: Fruit

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
                    val demoServiceName = demoService.name()
                    val service2Name = service1.name()
                    val fruitName = fruit.name()
                    Greeting(
                        "$demoServiceName\n$service2Name\n$fruitName\n" +
                                "$service1\n$service2",
                        modifier = Modifier.clickable {
                            startActivity(Intent(this, MainActivity2::class.java))
                        }
                    )
                }
            }
        }
    }
}
