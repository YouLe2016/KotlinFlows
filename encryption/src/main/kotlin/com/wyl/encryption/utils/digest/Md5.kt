package com.wyl.encryption.utils.digest

import java.security.MessageDigest

@OptIn(ExperimentalStdlibApi::class)
object Md5 : IMessageDigest {
    override fun digest(input: ByteArray): ByteArray {
        return MessageDigest.getInstance("MD5").digest(input)
    }

    override fun digestToString(input: String): String {
        return digest(input.toByteArray()).toHexString()
    }
}