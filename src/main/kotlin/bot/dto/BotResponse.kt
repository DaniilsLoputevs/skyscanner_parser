package bot.dto

import java.util.*

/**
 * Пятница 16
 * RIX - EMA
 * 12:25 - 13:15
 * Ryanair
 * 13 Фунтов(Персона)
 */
data class BotResponse(
    val date: Calendar,
    val from: String,
    val to: String,
    val departTime: String,
    val arriveTime: String,
    val company: String,
    val price: Double,
    val dir: String

) {
    override fun toString(): String {
        return "${date.get(Calendar.DAY_OF_WEEK)}\n" +
                "$from - $to\n" +
                "$departTime - $arriveTime\n" +
                "$company\n" +
                "$price\n" +
                "$dir\n"
    }
}
