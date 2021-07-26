package skyrequests.service

import bot.dto.BotResponse
import skyrequests.models.myservice.SkyServiceResponse

interface SkyParserService {

    public fun parseHttpResponse(data: SkyServiceResponse, options: Map<String, String> = mapOf()): List<BotResponse>

}
