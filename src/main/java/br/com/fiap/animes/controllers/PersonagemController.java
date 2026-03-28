package br.com.fiap.animes.controllers;

import br.com.fiap.animes.models.Personagem;
import br.com.fiap.animes.services.PersonagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personagens")
public class PersonagemController {
    @Autowired
    private PersonagemService service;

    @GetMapping
    public List<Personagem> ListAll(){
        return service.getAllPersonagens();
    }

    @GetMapping("anime/{id}")
    public List<Personagem> listAllByAnimeId(@PathVariable Long id) {
        return service.getAllPersonagensByAnimeId(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<Personagem> getPersonagemById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPersonagemById(id));
    }


    @PostMapping
    public ResponseEntity<Personagem> createPersonagem(@RequestBody Personagem personagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addPersonagem(personagem));
    }

    @PutMapping("{id}")
    public ResponseEntity<Personagem> updatePersonagem(@PathVariable Long id, @RequestBody Personagem personagem) {
        return ResponseEntity.ok(service.updatePersonagem(id, personagem));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePersonagem(@PathVariable Long id) {
        service.deletePersonagem(id);
        return ResponseEntity.noContent().build();
    }
}
