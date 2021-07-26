package skyrequests.models.myservice

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import skyrequests.models.skyserviceresponse.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class SkyServiceResponse (

    @JsonProperty("Quotes")
    val quotes : List<Quotes> = listOf(),
    @JsonProperty("Carriers")
    val carriers : List<Carriers> = listOf(),
    @JsonProperty("Places")
    val places : List<Places> = listOf(),
    @JsonProperty("Currencies")
    val currencies : List<Currencies> = listOf(),
    @JsonProperty("Routes")
    val routes : List<Routes> = listOf()
)
