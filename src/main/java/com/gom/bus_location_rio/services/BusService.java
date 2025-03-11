package com.gom.bus_location_rio.services;

import com.gom.bus_location_rio.client.BusRioClient;
import com.gom.bus_location_rio.dto.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {

    @Autowired
    private BusRioClient busRioClient;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<Bus> getAllBus() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime inicio = agora.minusSeconds(30);

        String dataInicial = inicio.format(FORMATTER);
        String dataFinal = agora.format(FORMATTER);

        return busRioClient.getBus(dataInicial, dataFinal);
    }

    public List<Bus> getBusByLine(String line) {
        List<Bus> allBuses = getAllBus();

        return allBuses.stream()
                .filter(bus -> bus.getLinha().equals(line))
                .collect(Collectors.toList());
    }
}
