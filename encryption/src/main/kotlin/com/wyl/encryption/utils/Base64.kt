package com.wyl.encryption.utils

import java.util.Base64

object Base64 {
    fun encode(input: ByteArray): ByteArray {
        return Base64.getEncoder().encode(input)
    }

    fun encode(input: String): ByteArray {
        return Base64.getEncoder().encode(input.toByteArray())
    }

    fun decode(input: ByteArray): ByteArray {
        return Base64.getDecoder().decode(input)
    }

    fun decode(input: String): ByteArray {
        return Base64.getDecoder().decode(input)
    }

    fun encodeToString(input: ByteArray): String {
        return Base64.getEncoder().encodeToString(input)
    }

    fun encodeToString(input: String): String {
        return Base64.getEncoder().encodeToString(input.toByteArray())
    }

    fun decodeToString(input: ByteArray): String {
        return String(Base64.getDecoder().decode(input))
    }

    fun decodeToString(input: String): String {
        return String(Base64.getDecoder().decode(input))
    }
}