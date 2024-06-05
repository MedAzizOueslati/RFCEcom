package com.rfc.rfcecommerce;

import io.unlogged.Unlogged;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication

public class RfcEcommerceApplication {
    @Unlogged
    public static void main(String[] args) {
        SpringApplication.run(RfcEcommerceApplication.class, args);
    }

}
