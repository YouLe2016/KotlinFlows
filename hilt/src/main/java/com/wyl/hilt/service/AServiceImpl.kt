package com.wyl.hilt.service

import javax.inject.Inject

class AServiceImpl @Inject constructor() : IDemoService {
    override fun name(): String {
        return "AService!!"
    }
}