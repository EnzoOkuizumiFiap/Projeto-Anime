package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Anime.dto.AnimeRequest;
import br.com.fiap.animes.Anime.dto.AnimeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("animes")
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeService service;

    @GetMapping
    public ResponseEntity<Page<AnimeResponse>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable).map(AnimeResponse::fromEntity));
    }

    @GetMapping("by-title/{title}")
    public ResponseEntity<Page<AnimeSummary>> findAllByTitle(@PathVariable String titulo, Pageable pageable) {
        return ResponseEntity.ok(service.findAllByTituloContaining(titulo, pageable));
    }

    @GetMapping("by-category/{categories}")
    public ResponseEntity<Page<AnimeSummary>> findAllByCategory(@PathVariable List<Categoria> categorias, Pageable pageable) {
        return ResponseEntity.ok(service.findAllByCategoria(categorias, pageable));
    }

    @GetMapping("by-date/{date}")
    public ResponseEntity<Page<AnimeSummary>> findAllByLaunch(@PathVariable LocalDate lancamento, Pageable pageable) {
        return ResponseEntity.ok(service.findAllByLancamento(lancamento, pageable));
    }

    @GetMapping("by-year-range")
    public ResponseEntity<Page<AnimeSummary>> findAllByLaunchPeriod(@RequestParam Integer from, Integer to, Pageable pageable) {
        return ResponseEntity.ok(service.findAllByPeriodoLancamento(from, to, pageable));
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
