package com.wyl.encryption.utils.digest

import com.wyl.encryption.utils.digest.IMessageDigest
import java.security.MessageDigest

@OptIn(ExperimentalStdlibApi::class)
object Sha1 : IMessageDigest {
    override fun digest(input: ByteArray): ByteArray {
        return MessageDigest.getInstance("SHA-1").digest(input)
    }

    override fun digestToString(input: String): String {
        return digest(input.toByteArray()).toHexString()
    }
}