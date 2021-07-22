package skyrequests.service

import bot.dto.BotResponse
import skyrequests.models.httpservice.SkyServiceResponse

interface SkyParserService {

    public fun parseHttpResponse(data: SkyServiceResponse): List<BotResponse>

}
