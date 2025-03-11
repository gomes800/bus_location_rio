package com.gom.bus_location_rio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BusLocationRioApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusLocationRioApplication.class, args);
	}

}
