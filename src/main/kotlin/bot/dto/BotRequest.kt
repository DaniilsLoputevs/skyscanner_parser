package bot.dto

data class BotRequest(
    val dates: List<String>,
    val from: String,
    val to: String,
    val currency : String, // GBP || EUR || RUB
    val options: List<String>,
)