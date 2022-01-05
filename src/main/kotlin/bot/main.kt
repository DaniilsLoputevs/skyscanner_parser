package bot

import com.elbekD.bot.Bot
import org.jsoup.Jsoup
import java.io.File
import java.io.FileInputStream
import java.util.*

const val REQ_FORMAT_MSG = "Пришлите запрос в формате:\n" +
        "DD.MM.YYYY  ИЛИ  DD.MM.YYYY-DD.MM.YYYY\n" +
        "FROM-TO\n" +
        "OPTIONS" +
        "\n\n" +
        "DD.MM.YYYY - один день\n" +
        "DD.MM.YYYY-DD.MM.YYYY - диапазон дней\n" +
        "OPTIONS - [DIR-NEAR-3]\n" +
        "  DIR - только Прямые перелёты\n" +
        "  NEAR - ближние аэропорты\n" +
        "  3 - кол-во пассажиров\n" +
        "\n\n" +
        "Примеры:\n" +
        "25.07.2021-27.07.2021\n" +
        "STN-RIX\n" +
        "DIR-NEAR\n" +
        "\n\n" +
        "16.07.2021\n" +
        "RIX-STN\n" +
        "DIR-NEAR-3"

/**
 * Telegram library : https://github.com/elbekD/kt-telegram-bot
 */
//fun main() {
//    val botApi = makeBotApi()
//    val botService = BotServiceImpl()
//    val skyScannerService = SkyScannerServiceImpl()
//
//
////    bot.chain("/start") { msg -> bot.sendMessage(msg.chat.id, "Hi! What is your name?") }
////        .then { msg -> bot.sendMessage(msg.chat.id, "Nice to meet you, ${msg.text}! Send something to me") }
////        .then { msg -> bot.sendMessage(msg.chat.id, "Fine! See you soon") }
////        .build()
//
//
//    botApi.chain("/hi") { msg -> botApi.sendMessage(msg.chat.id, REQ_FORMAT_MSG) }
//        .then { msg ->
//            val request = botService.parseMsg(msg.text ?: "")
//            val response = skyScannerService.process(request)
//            botApi.sendMessage(msg.chat.id, response.toString())
//        }
//        .build()
//
//
//    println("START!!!")
//    botApi.start()
//}

private fun makeBotApi(): Bot {
    val prop = loadConfig("app")

    val token = prop.getProperty("bot.token")
    val name = prop.getProperty("bot.name")
    return Bot.createPolling(name, token)
}

fun loadConfig(propName: String): Properties {
    val prop = Properties()
    FileInputStream(File("src/main/resources/$propName.properties")).use { prop.load(it) }

//    // Print all properties
//    prop.stringPropertyNames()
//        .associateWith { prop.getProperty(it) }
//        .forEach { println(it) }
    return prop
}

const val URL = "https://www.skyscanner.ru/transport/flights/rix/ltn/210801/210831/?adults=3&adultsv2=3&cabinclass=economy&children=0&childrenv2=&destinationentityid=27544008&inboundaltsenabled=false&infants=0&originentityid=27546172&outboundaltsenabled=false&preferdirects=true&preferflexible=false&ref=home&rtn=1"
fun main() {
    //1. Fetching the HTML from a given URL
//    Jsoup.connect("https://www.google.co.in/search?q=this+is+a+test").get().run {
    Jsoup.connect(URL).get().run {
        println(this)
//        2. Parses and scrapes the HTML response
////        select("div.rc").forEachIndexed { index, element ->
////            val titleAnchor = element.select("h3 a")
////            val title = titleAnchor.text()
////            val url = titleAnchor.attr("href")
//            3. Dumping Search Index, Title and URL on the stdout.
////            println("$index. $title ($url)")
//        }
    }
}


