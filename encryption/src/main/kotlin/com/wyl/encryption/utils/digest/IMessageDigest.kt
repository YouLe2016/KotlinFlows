package com.wyl.encryption.utils.digest

interface IMessageDigest {
    fun digest(input: ByteArray): ByteArray
    fun digestToString(input: String): String
}