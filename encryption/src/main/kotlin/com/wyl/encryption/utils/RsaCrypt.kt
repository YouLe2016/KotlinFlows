package com.wyl.encryption.utils

import java.security.Key
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object RsaCrypt {
    private const val ALGORITHM = "RSA"
    private const val TRANSFORMATION = ALGORITHM

    // 1024
    private const val ENCRYPT_MAX_SIZE = 117
    private const val DECRYPT_MAX_SIZE = 128
    // 2048
//    private const val ENCRYPT_MAX_SIZE = 245
//    private const val DECRYPT_MAX_SIZE = 256

    fun generateKeyPair(): KeyPair {
        val keyPair = KeyPairGenerator.getInstance(ALGORITHM).run {
            initialize(1024)
            generateKeyPair()
        }
        val publicKey = keyPair.public.encoded
        val privateKey = keyPair.private.encoded
        println("publicKey: ${Base64.encodeToString(publicKey)}")
        println("privateKey: ${Base64.encodeToString(privateKey)}")
        return keyPair
    }

    fun generateKeyPair(publicKey: String, privateKey: String): KeyPair {
        return KeyFactory.getInstance(ALGORITHM).let {
            KeyPair(
                it.generatePublic(X509EncodedKeySpec(Base64.decode(publicKey), ALGORITHM)),
                it.generatePrivate(PKCS8EncodedKeySpec(Base64.decode(privateKey), ALGORITHM))
            )
        }
    }

    private fun createCipher(mode: Int, key: Key): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(mode, key)
        }
    }

    fun encryptByPrivateKey(input: String, privateKey: PrivateKey): String {
        val byteArray = input.toByteArray()
        val cipher = createCipher(Cipher.ENCRYPT_MODE, privateKey)
        var offset = 0
        var temp: ByteArray = byteArrayOf()
        byteArray.toList()
            .chunked(ENCRYPT_MAX_SIZE)
            .forEach {
                // println("byteArray: ${byteArray.size}, offset: $offset, size: ${it.size}")
                temp += cipher.doFinal(byteArray, offset, it.size)
                offset += it.size
            }
        return Base64.encodeToString(temp)
    }

    fun decryptByPublicKey(input: String, publicKey: PublicKey): String {
        val byteArray = Base64.decode(input)
        val cipher = createCipher(Cipher.DECRYPT_MODE, publicKey)
        var offset = 0
        var temp: ByteArray = byteArrayOf()
        byteArray.toList()
            .chunked(DECRYPT_MAX_SIZE)
            .forEach {
                // println("byteArray: ${byteArray.size}, offset: $offset, size: ${it.size}")
                temp += cipher.doFinal(byteArray, offset, it.size)
                offset += it.size
            }
        return String(temp)
    }
}