package com.wyl.encryption

import com.wyl.encryption.utils.crypt.DesCrypt

fun a05_des() {
    val input = "你好啊"
    val password = "12345678"
    val encrypt = DesCrypt.encryptToString(input, password)
    println("加密后的数据：$encrypt")
    val decrypt = DesCrypt.decryptToString(encrypt, password)
    println("解密后的数据：$decrypt")
}