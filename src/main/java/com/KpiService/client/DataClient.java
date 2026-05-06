package com.KpiService.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.KpiService.dto.VentaResponse;
import com.KpiService.model.DetalleVenta;
import com.KpiService.model.Producto;

@Component
public class DataClient {

    private final WebClient webClient;

    @Value("${DATASERVICE_URL:http://localhost:8090}")
    private String baseUrl;

    // DataClient es un cliente que se conecta a DataService para obtener las ventas, usando WebClient
    public DataClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<VentaResponse> getVentas() {
        List<VentaResponse> ventas = webClient.get()
            .uri(baseUrl + "/api/v1/ventas/dto")
            .retrieve()
            .bodyToFlux(VentaResponse.class)
            .collectList()
            .block();

        return ventas != null ? ventas : List.of();
    }

    public List<DetalleVenta> getDetalleVentas() {
        List<DetalleVenta> detalles = webClient.get()
                .uri(baseUrl + "/api/v1/detalle-ventas")
                .retrieve()
                .bodyToFlux(DetalleVenta.class)
                .collectList()
                .block();

        return detalles != null ? detalles : List.of();
        }

        public List<Producto> getProductos() {
        List<Producto> productos = webClient.get()
            .uri(baseUrl + "/api/v1/productos")
            .retrieve()
            .bodyToFlux(Producto.class)
            .collectList()
            .block();

        return productos != null ? productos : List.of();
    }
}

// WebClient es una clase de Spring que permite hacer peticiones HTTP de manera reactiva 
// es decir sin bloquear el hilo principal lo que mejora el rendimiento y la escalabilidad 
// de la aplicacion. En este caso, se utiliza para hacer una petición GET a DataService y obtener la lista de ventas.