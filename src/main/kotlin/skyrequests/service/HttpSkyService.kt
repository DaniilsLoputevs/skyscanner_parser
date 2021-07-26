package skyrequests.service

import skyrequests.models.myservice.SkyServiceRequest
import skyrequests.models.myservice.SkyServiceResponse

interface HttpSkyService {

    public fun findFlyInfo(data: SkyServiceRequest): SkyServiceResponse

}
