package skyrequests.service

import bot.dto.BotRequest
import bot.dto.BotResponse

interface MainService {

    public fun provideData(botData: BotRequest): List<BotResponse>

}
