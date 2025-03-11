package com.gom.bus_location_rio.client;

import com.gom.bus_location_rio.config.FeignConfig;
import com.gom.bus_location_rio.dto.Bus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "busRio",
        url = "https://dados.mobilidade.rio/gps/sppo",
        configuration = FeignConfig.class
)
public interface BusRioClient {

    @GetMapping()
    List<Bus> getBus(
            @RequestParam("dataInicial") String dataInicial,
            @RequestParam("dataFinal") String dataFinal
    );

}
