package com.carros.api;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> buscarTipos(@PathVariable("tipo") String tipo){
        return service.getTipo(tipo);
    }

    @PostMapping()
    public String setCarro(@RequestBody Carro carro){
        service.setCarro(carro);
        return "Carro Salvo";
    }

    @DeleteMapping("/{id}")
    public String delCarro(@PathVariable("id") Long id){
        service.delCarro(id);
        return "Carro Deletado";
    }

    @PutMapping("/{id}")
    public String delCarro(@RequestBody Carro carro, @PathVariable("id") Long id){
        service.putCarro(carro, id);
        return "Carro Editado";
    }
}

