package skyrequests.service

import skyrequests.models.httpservice.SkyServiceRequest
import skyrequests.models.httpservice.SkyServiceResponse

interface HttpSkyService {

    public fun findFlyInfo(data: SkyServiceRequest): SkyServiceResponse

}
