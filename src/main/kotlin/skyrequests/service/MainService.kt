package skyrequests.service

import bot.dto.BotRequest
import bot.dto.BotResponse
import skyrequests.models.httpservice.SkyServiceRequest

interface MainService {

    public fun provideData(botData: BotRequest): List<BotResponse>

}
