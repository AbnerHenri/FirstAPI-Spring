package com.carros.api;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping()
    public Iterable<Carro> buscarCarros(){
        return service.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> buscarCarro(@PathVariable("id") Long id){
        return service.getCarro(id);
    }
}

