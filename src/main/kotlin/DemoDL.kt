import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

//class DemoDL {
//
//}
private val client = HttpClient.newBuilder().build()
private val jackson = JsonMapper
    .builder()
    .addModule(KotlinModule(strictNullChecks = false))
    .build()
private const val URL = ""

fun main() {
    println("URL = $URL")

    val request = HttpRequest.newBuilder()
        .uri(URI.create(URL))
        .build()
    client.send(request, HttpResponse.BodyHandlers.ofString())


}