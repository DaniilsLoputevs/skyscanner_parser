package skyrequests.service

import bot.dto.BotRequest
import bot.dto.BotResponse
import skyrequests.models.myservice.SkyServiceRequest

const val GET_COUNTRY = "country"
const val GET_LOCALE = "locale"
const val GET_DIR = "dir"
const val GET_NEAR = "near"
const val RU_COUNTRY = "ru"
const val RU_LOCALE = "ru-RU"


class MainServiceImpl : MainService {

    private val httpService: HttpSkyService = HttpSkyServiceImpl()
    private val parserService: SkyParserService = SkyParserServiceImp()

    override fun provideData(botData: BotRequest): List<BotResponse> {
        return botData.dates
            .map { SkyServiceRequest(
                it,
                botData.from,
                botData.to,
                botData.currency,
                botData.options[GET_COUNTRY] ?: RU_COUNTRY,
                botData.options[GET_LOCALE] ?: RU_LOCALE,
                botData.options.containsKey(GET_DIR),
                botData.options.containsKey(GET_NEAR)) }
            .map { httpService.findFlyInfo(it) }
            .flatMap { parserService.parseHttpResponse(it, botData.options) }
    }
}

fun main() {
    val dates = listOf("2021-09")
    val request = BotRequest(dates, "VKO", "LED", "rub", mapOf())
    val service = MainServiceImpl()
    val response = service.provideData(request)
    println(response)
}

