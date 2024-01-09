package com.wyl.encryption.utils.crypt

import com.wyl.encryption.utils.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import javax.crypto.spec.IvParameterSpec

object DesCrypt {
    private const val ALGORITHM = "DES"
    const val MODE_ECB = "$ALGORITHM/ECB/PKCS5Padding"
    const val MODE_CBC = "$ALGORITHM/CBC/PKCS5Padding"
    var transformation = MODE_CBC

    private fun createCipher(mode: Int, password: String): Cipher {
        return DESKeySpec(password.toByteArray())
            .let {
                SecretKeyFactory.getInstance(ALGORITHM)
                    .generateSecret(it)
            }.let {
                Cipher.getInstance(transformation).apply {
                    when (AesCrypt.transformation) {
                        AesCrypt.MODE_ECB -> {
                            init(mode, it)
                        }

                        AesCrypt.MODE_CBC -> {
                            init(mode, it, IvParameterSpec(password.take(8).toByteArray()))
                        }

                        else -> {
                            throw IllegalArgumentException("transformation is not supported")
                        }
                    }
                }
            }
    }

    /**
     * DES加密
     * @param input String
     * @param password String 必须是8位字节以上
     * @return ByteArray
     */
    fun encrypt(input: ByteArray, password: String): ByteArray {
        return createCipher(Cipher.ENCRYPT_MODE, password).doFinal(input)
    }

    fun decrypt(input: ByteArray, password: String): ByteArray {
        return createCipher(Cipher.DECRYPT_MODE, password).doFinal(input)
    }

    /**
     * DES加密
     * @param input String
     * @param password String 必须是8位字节以上
     * @return String
     */
    fun encryptToString(input: String, password: String): String {
        return Base64.encodeToString(encrypt(input.toByteArray(), password))
    }

    fun decryptToString(input: String, password: String): String {
        return String(decrypt(Base64.decode(input), password))
    }
}