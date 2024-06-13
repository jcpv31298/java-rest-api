package spring.boot.java_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.java_rest_api.service.IPokemonService;

@RestController
@RequestMapping(value = "/pokemon")
public class PokemonController {
    @Autowired
    private IPokemonService pokemonService;

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getPokemonByName(@PathVariable String name) {
        if(name.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        Object response = pokemonService.findByName(name);

        if (response == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
