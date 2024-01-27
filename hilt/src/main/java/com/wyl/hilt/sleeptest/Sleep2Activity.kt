package com.wyl.hilt.sleeptest

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wyl.hilt.R
import org.w3c.dom.Text

class Sleep2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep)

        findViewById<TextView>(R.id.button).text = "熄屏页面"
        findViewById<Button>(R.id.button2).apply {
            text = "返回"
            setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }
}