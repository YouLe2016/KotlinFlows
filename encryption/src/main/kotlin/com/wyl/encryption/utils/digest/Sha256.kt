package com.wyl.encryption.utils.digest

import java.security.MessageDigest

@OptIn(ExperimentalStdlibApi::class)
object Sha256 : IMessageDigest {
    override fun digest(input: ByteArray): ByteArray {
        return MessageDigest.getInstance("SHA-256").digest(input)
    }

    override fun digestToString(input: String): String {
        return digest(input.toByteArray()).toHexString()
    }
}