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
        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public Optional<CarroDTO> getCarro(Long id){
        return rep.findById(id).map(CarroDTO::create);
    }

     public List<CarroDTO> getTipo(String tipo){
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO setCarro(Carro carro){
        Assert.isNull(carro.getId(), "Registro não encontrador");
        return CarroDTO.create(rep.save(carro));
    }

    public boolean delCarro(Long id){
        if(rep.findById(id).isPresent()){
            rep.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public CarroDTO putCarro(Carro carro, Long id){
        Assert.notNull(id, "Não foi possível achar o carro");
        Optional<Carro> oCarro = rep.findById(id);
        if(oCarro.isPresent()){
            Carro db = oCarro.get();

            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());

            rep.save(db);

            return CarroDTO.create(db);
        }else{
            return null;
        }
    }
}
