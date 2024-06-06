package com.rfc.rfcecommerce;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RfcEcommerceApplicationTests {

    @Test
    void contextLoads() {
        assertNotNull( "Le contexte de l'application n'a pas été chargé");

    }

}
