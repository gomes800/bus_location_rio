package com.gom.bus_location_rio.config;

import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import feign.optionals.OptionalDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;

@Configuration
public class FeignConfig {

    @Bean
    public Decoder feignDecoder() {
        return new OptionalDecoder(new JacksonDecoder());
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> template.header("Accept", "application/json");
    }
}
