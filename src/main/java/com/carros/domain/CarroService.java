package com.carros.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public Iterable<Carro> getCarros() {
        return rep.findAll();
    }

    public Optional<Carro> getCarro(Long id){
        return rep.findById(id);
    }

     public Iterable<Carro> getTipo(String tipo){
        return rep.findByTipo(tipo);
    }

    public void setCarro(Carro carro){
        rep.save(carro);
    }

    public void delCarro(Long id){
        rep.deleteById(id);
    }
}
