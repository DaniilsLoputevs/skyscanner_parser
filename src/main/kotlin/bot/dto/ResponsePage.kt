package bot.dto

class ResponsePage(
    val responses: List<BotResponse>
) {
    /**
     * TODO : сделать разделение на разные дни если у нас диапазон дней.
     */
    override fun toString(): String {
        if (responses.isEmpty()) return ""

        val rsl = StringBuilder()
        val prevDate = responses[0].date
        responses.forEach {
            if (it.date.before(prevDate)) {
                rsl.append(it)
            } else {
                rsl.append("\n\n=========================\n\n")
                rsl.append(it)
            }
        }
        return rsl.toString()
    }
}