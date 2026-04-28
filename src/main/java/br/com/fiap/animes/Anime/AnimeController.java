package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Anime.dto.AnimeRequest;
import br.com.fiap.animes.Anime.dto.AnimeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("animes")
@RequiredArgsConstructor
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class AnimeController {
    private final AnimeService service;

    @GetMapping
    public ResponseEntity<Page<AnimeResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable).map(AnimeResponse::fromEntity));
    }

    @GetMapping("by-title")
    public ResponseEntity<Page<AnimeResponse>> findAllByTitle(@RequestParam String titulo, Pageable pageable) {
        return ResponseEntity.ok(service.findAllByTitulo(titulo, pageable).map(AnimeResponse::fromEntity));
    }

    @GetMapping("by-date")
    public ResponseEntity<Page<AnimeProjections>> findAllByLancamento(@RequestParam LocalDate lancamento, Pageable pageable) {
        return ResponseEntity.ok(service.findAllByLancamento(lancamento, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(AnimeResponse.fromEntity(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<AnimeResponse> create(@RequestBody @Valid AnimeRequest animeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AnimeResponse.fromEntity(service.create(animeRequest)));
    }

    @PutMapping("{id}")
    public ResponseEntity<AnimeResponse> update(@PathVariable Long id, @RequestBody @Valid AnimeRequest animeRequest) {
        return ResponseEntity.ok(AnimeResponse.fromEntity(service.update(id, animeRequest)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
