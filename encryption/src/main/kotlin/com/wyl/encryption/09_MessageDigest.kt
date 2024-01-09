package com.wyl.encryption

import com.wyl.encryption.utils.digest.Md5
import com.wyl.encryption.utils.digest.Sha1
import com.wyl.encryption.utils.digest.Sha256

fun a09_MessageDigest() {
    // md5 = 8e25100017ee33ad569efea3e6f2b720
    // sha1 = 1c3797d0b5bcf4559bcd8db6352de70eb8280fc6
    // sha256 = c85629c34f30189793c3a6b9ed812ba246c2eeb6205b73625331529beb93ca96
    val input = "你好啊！"
    val md5 = Md5.digestToString(input)
    println("md5 = $md5")
    val sha1 = Sha1.digestToString(input)
    println("sha1 = $sha1")
    val sha256 = Sha256.digestToString(input)
    println("sha256 = $sha256")
}

fun myPrint(str: String) {
    println(
        String.format(
            "%-40s%s",
            *(str.split("=").toTypedArray())
        )
    )
}