package bot

import com.elbekD.bot.Bot
import com.elbekD.bot.feature.chain.chain
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * Telegram library : https://github.com/elbekD/kt-telegram-bot
 */
fun main() {
    val prop = loadConfig("app")

    println("hlb")

    val token = prop.getProperty("bot.token")
    val name = prop.getProperty("bot.name")
    val bot = Bot.createPolling(name, token)

//    bot.onCommand("/start") { msg, _ ->
//        bot.sendMessage(msg.chat.id, "Hello World!")
//    }
    bot.chain("/start") { msg -> bot.sendMessage(msg.chat.id, "Hi! What is your name?") }
        .then { msg -> bot.sendMessage(msg.chat.id, "Nice to meet you, ${msg.text}! Send something to me") }
        .then { msg -> bot.sendMessage(msg.chat.id, "Fine! See you soon") }
        .build()

    bot.start()
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
