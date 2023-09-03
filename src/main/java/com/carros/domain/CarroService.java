package com.carros.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros() {
        return rep.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());
    }

    public Optional<Carro> getCarro(Long id){
        return rep.findById(id);
    }

     public List<CarroDTO> getTipo(String tipo){
        return rep.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
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
        }else{
            throw new RuntimeException("Não foi possivel localizar o carro");
        }
    }
}
