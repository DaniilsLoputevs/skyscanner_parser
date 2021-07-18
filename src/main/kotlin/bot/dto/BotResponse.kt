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
    val date: Date, // TODO Показывать день недели(Может использовать Типа Calendar)
    val from: String,
    val to: String,
    val departTime: String,
    val arriveTime: String,
    val company: String,
    val price: Double

) {
    override fun toString(): String {
        return "$date\n" +
                "$from - $to\n" +
                "$departTime - $arriveTime\n" +
                "$company\n" +
                "$price\n"
    }
}