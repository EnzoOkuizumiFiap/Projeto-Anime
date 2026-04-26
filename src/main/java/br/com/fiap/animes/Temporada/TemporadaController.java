package br.com.fiap.animes.Temporada;

import br.com.fiap.animes.Temporada.dto.TemporadaRequest;
import br.com.fiap.animes.Temporada.dto.TemporadaResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temporadas")
@RequiredArgsConstructor
public class TemporadaController {

    private final TemporadaService service;

    @GetMapping
    public List<TemporadaResponse> findAll() {
        return service.findAll()
                .stream()
                .map(TemporadaResponse::fromEntity)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<TemporadaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(TemporadaResponse.fromEntity(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<TemporadaResponse> create(@RequestBody @Valid TemporadaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TemporadaResponse.fromEntity(service.create(request)));
    }

    @PutMapping("{id}")
    public ResponseEntity<TemporadaResponse> update(@PathVariable Long id, @RequestBody @Valid TemporadaRequest request) {
        return ResponseEntity.ok(TemporadaResponse.fromEntity(service.update(id, request)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
