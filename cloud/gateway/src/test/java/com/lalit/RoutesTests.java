package com.lalit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureWireMock(port = 0)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties
        = {
        "PLAYERS_URI=http://localhost:${wiremock.server.port}",
        "ALBUMS_URI=http://localhost:${wiremock.server.port}",
})
public class RoutesTests {
    @Autowired
    private WebTestClient webClient;
}