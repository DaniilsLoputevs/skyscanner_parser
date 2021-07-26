package skyrequests.models.myservice

/**
 *
 * 16.07.2021
 * RIX-STN
 * GBP
 * DIR-NEAR
 *
 */

data class SkyServiceRequest(

    val startDate: String,
    val from: String,
    val to: String,
    val currency: String = "rub",
    val country: String ="RU",
    val locale: String = "ru-RU",
    val dir: Boolean = true,
    val near: Boolean = false
)
