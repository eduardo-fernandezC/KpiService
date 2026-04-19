package com.KpiService.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.KpiService.model.Venta;

@Component
public class DataClient {

    private final WebClient webClient;

    @Value("${DATASERVICE_URL:http://localhost:8090}")
    private String baseUrl;

    public DataClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<Venta> getVentas() {
        return webClient.get()
                .uri(baseUrl + "/api/v1/ventas")
                .retrieve()
                .bodyToFlux(Venta.class)
                .collectList()
                .block();
    }
}