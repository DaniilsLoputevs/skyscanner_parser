package skyrequests.service

import bot.dto.BotRequest
import bot.dto.BotResponse
import skyrequests.models.httpservice.SkyServiceRequest

class MainServiceImpl : MainService {

    private val httpService: HttpSkyService = HttpSkyServiceImpl()
    private val parserService: SkyParserService = SkyParserServiceImp()

    override fun provideData(botData: BotRequest): List<BotResponse> {
        return botData.dates
            .map { SkyServiceRequest(it, botData.from, botData.to, botData.currency) }
            .map { httpService.findFlyInfo(it) }
            .flatMap { parserService.parseHttpResponse(it) }
    }
}

fun main() {
    val dates = listOf("2021-08")
    val request = BotRequest(dates, "VKO", "KRR", "rub", dates)
    val service = MainServiceImpl()
    val response = service.provideData(request)
    println(response)
}

