import com.wyl.generics.part1.Point
import org.junit.Test

class TestKotlin {
    @Test
    fun test() {
        var point: Point<*>
        point = Point<Int>(3, 3)
        point = Point<Float>(4.3f, 4.3f)
        point = Point<Double>(4.3, 4.90)
        point = Point<Long>(12L, 23L)
        point = Point<String>("", "")
        point = Point<Any>(Any(), Any())
        println(point)
    }

    @Test
    fun test3() {
        var point: Point<out Number>
        point = Point<Number>(3, 3)
        point = Point<Int>(3, 3)
        point = Point<Float>(4.3f, 4.3f)
        point = Point<Double>(4.3, 4.90)
        point = Point<Long>(12L, 23L)
        // 以下两行报错
        // point = Point<String>("", "")
        // point = Point<Any>(Any(), Any())
        println(point)

        // 这点和Java不一样啊，取出来直接是Long，Kotlin很聪明
        val x: Long = point.x
        // 这行可以运行过
        point.x = 13L
        // 这行报错
        // point.x = 12.0
        println(point.x)
    }
}