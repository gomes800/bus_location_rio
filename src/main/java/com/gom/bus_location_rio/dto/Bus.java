package com.gom.bus_location_rio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bus {

    private String ordem;

    @Getter(AccessLevel.NONE)
    @JsonProperty("latitude")
    private String latitudeRaw;

    @Getter(AccessLevel.NONE)
    @JsonProperty("longitude")
    private String longitudeRaw;

    @JsonProperty("datahora")
    private long dataHora;

    @JsonProperty("velocidade")
    private int velocidade;

    private String linha;

    @JsonProperty("datahoraenvio")
    private long dataHoraEnvio;

    @JsonProperty("datahoraservidor")
    private long dataHoraServidor;

    @JsonProperty("latitude")
    public double getLatitude() {
        return parseCoordinate(latitudeRaw);
    }

    @JsonProperty("longitude")
    public double getLongitude() {
        return parseCoordinate(longitudeRaw);
    }

    private double parseCoordinate(String rawCoordinate) {
        if (rawCoordinate == null || rawCoordinate.isEmpty()) {
            return 0.0;
        }

        try {
            String formattedCoordinate = rawCoordinate.replace(",", ".");
            return Double.parseDouble(formattedCoordinate);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter a coordenada: " + e.getMessage());
            return 0.0;
        }
    }
}
