package com.wyl.reflect.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun calculateWeekAndDate(dateStr: String, format: String = "yyyy-MM-dd") {
    val calendar = Calendar.getInstance().apply {
        time = SimpleDateFormat(format, Locale.CHINA).parse(dateStr)
    }
    println(
        // week-num: {{date:WW}}
        // day-num: {{date:DDD}}
        "$dateStr: ${calendar.get(Calendar.WEEK_OF_YEAR)},${
            calendar.get(
                Calendar.DAY_OF_YEAR
            )
        }"
    )
}

fun calculateWeekAndDateWithTimestamp(dateStr: String) {
    calculateWeekAndDate(dateStr, "yyyyMMdd")
}

fun main() {
    calculateWeekAndDate("2023-12-15")
    calculateWeekAndDateWithTimestamp("20231220")
}
