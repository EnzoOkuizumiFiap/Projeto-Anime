package br.com.fiap.animes.Personagem;

import br.com.fiap.animes.Personagem.dto.PersonagemRequest;
import br.com.fiap.animes.Personagem.dto.PersonagemResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("personagens")
public class PersonagemController {
    private final PersonagemService service;

    @GetMapping
    public List<PersonagemResponse> findAll() {
        return service.findAll()
                .stream()
                .map(PersonagemResponse::fromEntity)
                .toList();
    }

    @GetMapping("anime/{id}")
    public ResponseEntity<List<PersonagemResponse>> findAllByAnimeId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findAllByAnimeId(id)
                .stream()
                .map(PersonagemResponse::fromEntity)
                .toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<PersonagemResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(PersonagemResponse.fromEntity(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<PersonagemResponse> create(@RequestBody @Valid PersonagemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PersonagemResponse.fromEntity(service.create(request)));
    }

    @PutMapping("{id}")
    public ResponseEntity<PersonagemResponse> update(@PathVariable Long id, @RequestBody PersonagemRequest request) {
        return ResponseEntity.ok(PersonagemResponse.fromEntity(service.update(id, request)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
