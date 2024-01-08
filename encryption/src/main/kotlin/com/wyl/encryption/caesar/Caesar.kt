package com.wyl.encryption.caesar

object Caesar {
    fun encrypt(str: String, key: Int): String {
        val charArray = str.toCharArray()
        charArray.forEachIndexed { index, c ->
            charArray[index] = (c.code + key).toChar()
        }
        return String(charArray)
    }

    fun decrypt(str: String, key: Int): String {
        val charArray = str.toCharArray()
        charArray.forEachIndexed { index, c ->
            charArray[index] = (c.code - key).toChar()
        }
        return String(charArray)
    }
}