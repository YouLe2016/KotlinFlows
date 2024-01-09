package com.wyl.encryption

import com.wyl.encryption.utils.Signature
import com.wyl.encryption.utils.crypt.RsaCrypt

fun a10_Signature() {
    val input = "name=iPhone14&price=9999"
    val keyPair = RsaCrypt.generateKeyPair()
    val sign = Signature.sign(input, keyPair.private)
    println("签名：$sign")
    val verify = Signature.verify(input, keyPair.public, sign)
    println("验签：$verify")
}