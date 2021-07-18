package bot.services

import bot.dto.BotRequest

/**
 * 16.07.2021-17.07.2021 - диапазон дат или одна дата.
 * RIX-STN - from & to
 * DIR-NEAR - TODO : в будущем.
 */
interface BotService {
    fun parseMsg(msgText: String): BotRequest

}

class BotServiceImpl : BotService {
    override fun parseMsg(msgText: String): BotRequest {
        val txt = msgText.split("\n")
        val fromAndTo = txt[1].split("-")
//        txt.forEachIndexed{ i, it ->println("$i = $it")}

        return BotRequest(
            dates = txt[0].split("-").toList(), // может быть 1 или 2 значения, EXCEPTION нету.
            from = fromAndTo[0],
            to = fromAndTo[1],
            currency = txt[2],
            options = listOf()
        )

    }
}