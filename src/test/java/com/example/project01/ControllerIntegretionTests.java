package com.example.project01;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegretionTests {
    @Test
    @Order(1)
    void InTest_getAllCountries(){
        TestRestTemplate restTemplate = new TestRestTemplate();
       ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries",String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
    }
}
