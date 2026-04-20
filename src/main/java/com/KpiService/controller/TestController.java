// este controller solo es de pruebas que se conecte a DataService y traiga las ventas, para probar que 
// el feign client funciona correctamente, luego se borra este controller
package com.KpiService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KpiService.client.DataClient;
import com.KpiService.model.Venta;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DataClient dataClient;

    @GetMapping("/ventas")
    public List<Venta> testVentas() {
        return dataClient.getVentas();
    }
}

// http://localhost:8092/test/ventas