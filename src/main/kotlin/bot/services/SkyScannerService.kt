package bot.services

import bot.dto.BotRequest
import bot.dto.BotResponse
import bot.dto.ResponsePage
import java.util.*

/**
 * Где-то, тут происходит вся твоя HTTP магия. )))
 */
interface SkyScannerService {
    fun process(req: BotRequest): ResponsePage
}

class SkyScannerServiceImpl : SkyScannerService {

    override fun process(req: BotRequest): ResponsePage {
//        TODO("Not yet implemented")

        return ResponsePage(
            responses = listOf(
                BotResponse(
                    date = GregorianCalendar().apply { Date() },
                    from = "RIX",
                    to = "STN",
                    departTime = "22:05",
                    arriveTime = "22:45",
                    company = "Ryanair",
                    price = 0.0,
                    dir = "true"
                )
            )
        )
    }

}
