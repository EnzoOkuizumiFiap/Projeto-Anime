package br.com.fiap.animes.Temporada;

import br.com.fiap.animes.Anime.dto.AnimeRequest;
import br.com.fiap.animes.Temporada.dto.TemporadaRequest;
import br.com.fiap.animes.Temporada.dto.TemporadaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temporadas")
@RequiredArgsConstructor
public class TemporadaController {

    private TemporadaService service;

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
    public Temporada create(@RequestBody Temporada temporada) {
        return service.create(temporada);
    }

    @PutMapping("{id}")
    public ResponseEntity<TemporadaResponse> update(@PathVariable Long id, @RequestBody TemporadaRequest temporadaRequest) {
        return ResponseEntity.ok(TemporadaResponse.fromEntity(service.update(id, temporadaRequest.toEntity())));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
