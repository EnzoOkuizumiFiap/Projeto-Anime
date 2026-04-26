package br.com.fiap.animes.Personagem;

import br.com.fiap.animes.Personagem.dto.PersonagemRequest;
import br.com.fiap.animes.Personagem.dto.PersonagemResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("personagens")
public class PersonagemController {
    private final PersonagemService service;

    @GetMapping
    public ResponseEntity<Page<PersonagemResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable).map(PersonagemResponse::fromEntity));
    }

    @GetMapping("anime/{id}")
    public ResponseEntity<Page<PersonagemResponse>> findAllByAnimeId(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(service.findAllByAnimeId(id, pageable).map(PersonagemResponse::fromEntity));
    }

    @GetMapping("by-name/{nome}")
    public ResponseEntity<Page<PersonagemProjections>> findAllByNome(@PathVariable String nome, Pageable pageable) {
        return ResponseEntity.ok(service.findByNome(nome, pageable));
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
    public ResponseEntity<PersonagemResponse> update(@PathVariable Long id, @RequestBody @Valid PersonagemRequest request) {
        return ResponseEntity.ok(PersonagemResponse.fromEntity(service.update(id, request)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
