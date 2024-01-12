package com.wyl.hilt.service

import javax.inject.Inject

class DemoService @Inject constructor() {
    fun name(): String {
        return "DemoService!!"
    }
}