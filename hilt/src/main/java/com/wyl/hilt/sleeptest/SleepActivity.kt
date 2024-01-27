package com.wyl.hilt.sleeptest

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wyl.hilt.R

class SleepActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_sleep)

        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(Intent(this, Sleep2Activity::class.java))
        }

        findViewById<Button>(R.id.btnClearFlags).apply {
            setOnClickListener {
                if (text == "clear") {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                    text = "add"
                } else {
                    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                    text = "clear"
                }
            }
        }
    }
}