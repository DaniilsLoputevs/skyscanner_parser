package skyrequests.service

import skyrequests.models.myservice.SkyServiceRequest

interface SkyNearFinder {

    public fun getNeatPlaces(request: SkyServiceRequest): List<SkyServiceRequest>

}
