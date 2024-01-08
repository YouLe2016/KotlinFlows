package com.wyl.encryption.utils

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AesCrypt {
    private const val ALGORITHM = "AES"
    const val MODE_ECB = "$ALGORITHM/ECB/PKCS5Padding"
    const val MODE_CBC = "$ALGORITHM/CBC/PKCS5Padding"
    var transformation = MODE_CBC

    private fun createCipher(mode: Int, password: String): Cipher {
        return SecretKeySpec(password.toByteArray(), ALGORITHM).let {
            Cipher.getInstance(transformation).apply {
                when (transformation) {
                    MODE_ECB -> {
                        init(mode, it)
                    }

                    MODE_CBC -> {
                        init(mode, it, IvParameterSpec(password.toByteArray()))
                    }

                    else -> {
                        throw IllegalArgumentException("transformation is not supported")
                    }
                }
            }
        }
    }

    /**
     * AES加密
     * @param input String
     * @param password String 必须是16位字节
     * @return ByteArray
     */
    fun encrypt(input: ByteArray, password: String): ByteArray {
        return createCipher(Cipher.ENCRYPT_MODE, password).doFinal(input)
    }

    fun decrypt(input: ByteArray, password: String): ByteArray {
        return createCipher(Cipher.DECRYPT_MODE, password).doFinal(input)
    }

    /**
     * AES加密
     * @param input String
     * @param password String 必须是16位字节
     * @return String
     */
    fun encryptToString(input: String, password: String): String {
        return Base64.encodeToString(encrypt(input.toByteArray(), password))
    }

    fun decryptToString(input: String, password: String): String {
        return String(decrypt(Base64.decode(input), password))
    }
}