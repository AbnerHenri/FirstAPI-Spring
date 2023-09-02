package com.carros.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public void putCarro(Carro carro, Long id){
        Assert.notNull(id, "Não foi possível achar o carro");
        Optional<Carro> oCarro = getCarro(id);
        if(oCarro.isPresent()){
            Carro db = oCarro.get();

            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());

            rep.save(db);
        }
    }
}
