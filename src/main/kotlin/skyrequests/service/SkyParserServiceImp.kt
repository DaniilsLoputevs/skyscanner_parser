package skyrequests.service

import bot.dto.BotResponse
import skyrequests.models.httpservice.SkyServiceResponse
import skyrequests.models.httpservice.skymodels.Carriers
import skyrequests.models.httpservice.skymodels.Places
import skyrequests.models.httpservice.skymodels.Quotes
import java.text.SimpleDateFormat
import java.util.*

class SkyParserServiceImp : SkyParserService {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val carrierMap = mutableMapOf<Int, Carriers>()
    val placeMap = mutableMapOf<Int, Places>()

    override fun parseHttpResponse(data: SkyServiceResponse): List<BotResponse> {
        val quotesMap = mutableMapOf<Int, Quotes>()
        with(data) {
            quotes.forEach { quotesMap[it.quoteId] = it }
            carriers.forEach { carrierMap[it.carrierId] = it }
            places.forEach { placeMap[it.placeId] = it }
            return routes
                .filter { quotesMap.containsKey(it) && quotesMap[it]?.direct ?: false}
                .map { rout ->
                    BotResponse(
                        makeDate(rout.quoteDateTime),
                        placeMap[rout.originId].let { it?.cityName ?: "Error city from name" },
                        placeMap[rout.destinationId].let { it?.cityName ?: "Error city to name" },
                        quotesMap[rout.quoteIds].let { it?.outboundLeg?.departureDate ?: "None"},
                        "None",
                        carrierMap[quotesMap[rout.originId]?.quoteId]?.name ?: "none",
                        rout.price.toDouble()
                    )
                }
        }
    }

    private fun makeDate(strDate: String): Date {
        return dateFormat.parse(strDate)
    }
}
