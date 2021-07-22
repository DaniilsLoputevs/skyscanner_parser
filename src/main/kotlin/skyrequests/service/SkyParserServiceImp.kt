package skyrequests.service

import bot.dto.BotResponse
import skyrequests.models.httpservice.SkyServiceResponse
import skyrequests.models.httpservice.skymodels.Carriers
import skyrequests.models.httpservice.skymodels.Places

class SkyParserServiceImp : SkyParserService {

    val carrierMap = mutableMapOf<Int, Carriers>()
    val placeMap = mutableMapOf<Int, Places>()

    override fun parseHttpResponse(data: SkyServiceResponse): List<BotResponse> {
        data.carriers.forEach { carrierMap.put(it.carrierId, it) }
        return listOf()
    }
}
