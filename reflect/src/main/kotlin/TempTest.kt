import com.wyl.util.ImageCompress


fun main() {
//    var char = 'a'
//    println(char.code)
//    char = '您'
//    println(char.code)
//
//    var str = "I love you"
//    str.map { it.code }
//        .joinToString(" ")
//        .let(::println)
//
//    str = ('a'..'z').joinToString { it.code.toString() }
//    println(str)
//    str = ('A'..'Z').joinToString { it.code.toString() }
//    println(str)
//    str = ('0'..'9').joinToString { it.code.toString() }
//    println(str)
//    println((65..122).map { Char(it).toString() })

    ImageCompress.compressPic("1.jpg", "0.6.jpg")
    ImageCompress.compressPic("1.png", "0.6.png")


}
