package com.gom.bus_location_rio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "busRio", url = "url_base")
public interface BusRioClient {


}
