package com.wyl.encryption.utils

import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature

object Signature {
    fun sign(input: String, privateKey: PrivateKey): String {
        return Base64.encodeToString(
            sign(input.toByteArray(), privateKey)
        )
    }

    fun sign(input: ByteArray, privateKey: PrivateKey): ByteArray {
        val signature = Signature.getInstance("SHA1withRSA").apply {
            initSign(privateKey)
            update(input)
        }
        return signature.sign()
    }

    fun verify(input: ByteArray, publicKey: PublicKey, sign: ByteArray): Boolean {
        val signature = Signature.getInstance("SHA1withRSA").apply {
            initVerify(publicKey)
            update(input)
        }
        return signature.verify(sign)
    }

    fun verify(input: String, publicKey: PublicKey, sign: String): Boolean {
        return verify(input.toByteArray(), publicKey, Base64.decode(sign))
    }
}