package skyrequests.service

import bot.dto.BotResponse
import skyrequests.models.myservice.SkyServiceResponse
import skyrequests.models.skyserviceresponse.Carriers
import skyrequests.models.skyserviceresponse.Places
import skyrequests.models.skyserviceresponse.Quotes
import java.text.SimpleDateFormat
import java.util.*

class SkyParserServiceImp : SkyParserService {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val carrierMap = mutableMapOf<Int, Carriers>()
    val placeMap = mutableMapOf<Int, Places>()

    override fun parseHttpResponse(data: SkyServiceResponse, options: Map<String, String>): List<BotResponse> {
        val quotesMap = mutableMapOf<Int, Quotes>()
        with(data) {
            quotes.forEach { quotesMap[it.quoteId] = it }
            carriers.forEach { carrierMap[it.carrierId] = it }
            places.forEach { placeMap[it.placeId] = it }
            return routes
                .flatMap {
                    it.quoteIds.map { qu -> Pair(it,qu) }
                }
                .filter { quotesMap[it.second] != null
                        && (options[GET_DIR] == "false" || quotesMap[it.second]?.direct ?: false) }
                .map { pair ->
                    BotResponse(
                        makeDate(quotesMap[pair.second].let { it?.outboundLeg?.departureDate ?: "1970-01-01T00:00:00"}),
                        placeMap[pair.first.originId].let { it?.cityName ?: "Error city from name" },
                        placeMap[pair.first.destinationId].let { it?.cityName ?: "Error city to name" },
                        quotesMap[pair.second].let { it?.outboundLeg?.departureDate ?: "None"}, //TODO решить вопрос с часами отправления
                        "None", //TODO Решить вопрос с времнем возвращения
                        quotesMap[pair.second]?.outboundLeg?.carrierIds?.fold("")
                        { total, it -> total + carrierMap[it]?.name + ", "} ?: "None",
                        pair.first.price.toDouble(),
                        quotesMap[pair.second]?.direct.toString()
                    )
                }
                .also {
                    println(it)
                }
        }
    }

    private fun makeDate(strDate: String): Calendar {
        return GregorianCalendar().apply { time =  dateFormat.parse(strDate) }
    }
}

