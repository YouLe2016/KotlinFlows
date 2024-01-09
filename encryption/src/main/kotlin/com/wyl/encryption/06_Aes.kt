package com.wyl.encryption

import com.wyl.encryption.utils.crypt.AesCrypt

fun a06_aes() {
    val input = "你好啊"
    val password = "0123456789abcdef"
    val encrypt = AesCrypt.encryptToString(input, password)
    println("加密后的数据：$encrypt")
    val decrypt = AesCrypt.decryptToString(encrypt, password)
    println("解密后的数据：$decrypt")
}