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
                .flatMap {
                    it.quoteIds.map { qu -> Pair(it,qu) }
                }
                .filter { quotesMap[it.second] != null }
                .map { pair ->
                    BotResponse(
                        makeDate(pair.first.quoteDateTime),
                        placeMap[pair.first.originId].let { it?.cityName ?: "Error city from name" },
                        placeMap[pair.first.destinationId].let { it?.cityName ?: "Error city to name" },
                        quotesMap[pair.second].let { it?.outboundLeg?.departureDate ?: "None"},
                        "None",
                        quotesMap[pair.second]?.outboundLeg?.carrierIds?.fold("")
                        { total, it -> total + carrierMap[it]?.name + ", "} ?: "None",
                        pair.first.price.toDouble()
                    )
                }
                .also {
                    println(it)
                }
        }
    }

    private fun makeDate(strDate: String): Date {
        return dateFormat.parse(strDate)
    }
}
