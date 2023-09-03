package com.carros.api;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService service;

    @GetMapping()
    public List<CarroDTO> buscarCarros(){
        return service.getCarros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> buscarCarro(@PathVariable("id") Long id){
        Optional<CarroDTO> carro = service.getCarro(id);

        return carro.isPresent() ? ResponseEntity.ok(carro.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> buscarTipos(@PathVariable("tipo") String tipo){
        List<CarroDTO> carros = service.getTipo(tipo);

        return carros.isEmpty() ?
            ResponseEntity.noContent().build():
            ResponseEntity.ok(carros);
    }

    @PostMapping()
    public ResponseEntity<String> setCarro(@RequestBody Carro carro){

        try{
            Long c = service.setCarro(carro);
            URI location = getURI(c);
            return ResponseEntity.created(location).build();
        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }     
    }

    @DeleteMapping("/{id}")
    public String delCarro(@PathVariable("id") Long id){
        service.delCarro(id);
        return "Carro Deletado";
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putCarro(@RequestBody Carro carro, @PathVariable("id") Long id){
    
        carro.setId(id);
        CarroDTO c = service.putCarro(carro, id);
        return c != null ? 
            ResponseEntity.ok("Carro Editado") : ResponseEntity.notFound().build(); 
    }

    private URI getURI(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(id).toUri();
    }
}

