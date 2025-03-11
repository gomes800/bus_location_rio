package com.gom.bus_location_rio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bus {

    private String ordem;

    @JsonProperty("latitude")
    private String latitudeRaw;

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

    @JsonIgnore
    public double getLatitude() {
        return Double.parseDouble(latitudeRaw.replace(",", "."));
    }

    @JsonIgnore
    public double getLongitude() {
        return Double.parseDouble(longitudeRaw.replace(",", "."));
    }
}
