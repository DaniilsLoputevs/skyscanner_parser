package skyrequests.service

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import skyrequests.models.httpservice.SkyServiceRequest
import skyrequests.models.httpservice.SkyServiceResponse
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

const val SKYURI = "http://partners.api.skyscanner.net/apiservices/browseroutes/v1.0"

class HttpSkyServiceImpl: HttpSkyService {

    private val client = HttpClient.newBuilder().build()
    private val jackson = JsonMapper.builder()
        .addModule(KotlinModule(strictNullChecks = false))
        .build()

    override fun findFlyInfo(data: SkyServiceRequest): SkyServiceResponse {
        val currentURI = "$SKYURI/${data.country}/${data.currency}/${data.locale}/${data.from}/${data.to}" +
                "/${data.startDate}?apiKey=prtl6749387986743898559646983194"
        val request = HttpRequest
            .newBuilder()
            .uri(URI.create(currentURI))
            .build()
        return jackson
            .readValue(client.send(request, HttpResponse.BodyHandlers.ofString()).body())
    }
}

