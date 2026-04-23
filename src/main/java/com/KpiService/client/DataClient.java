package com.KpiService.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.KpiService.model.DetalleVenta;
import com.KpiService.model.Venta;

@Component
public class DataClient {

    private final WebClient webClient;

    @Value("${DATASERVICE_URL:http://localhost:8090}")
    private String baseUrl;

    // DataClient es un cliente que se conecta a DataService para obtener las ventas, usando WebClient
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

    public List<DetalleVenta> getDetalleVentas() {
        return webClient.get()
                .uri(baseUrl + "/api/v1/detalle-ventas")
                .retrieve()
                .bodyToFlux(DetalleVenta.class)
                .collectList()
                .block();
    }

}

// WebClient es una clase de Spring que permite hacer peticiones HTTP de manera reactiva 
// es decir sin bloquear el hilo principal lo que mejora el rendimiento y la escalabilidad 
// de la aplicacion. En este caso, se utiliza para hacer una petición GET a DataService y obtener la lista de ventas.