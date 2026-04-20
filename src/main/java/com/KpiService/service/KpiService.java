package com.KpiService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KpiService.client.DataClient;
import com.KpiService.dto.KpiResponse;
import com.KpiService.model.Venta;

@Service
public class KpiService {

    @Autowired
    private DataClient dataClient; // inyectamos el cliente para obtener los datos

    public KpiResponse ventasTotales() {
        List<Venta> ventas = dataClient.getVentas(); // obtenemos las ventas desde el microservicio de datos

        double totalVentas = ventas.stream()
                .mapToDouble(Venta::getTotal)
                .sum();

        return new KpiResponse("Ventas Totales", totalVentas);
    }
}
