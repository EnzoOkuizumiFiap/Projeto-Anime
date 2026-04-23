package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Anime.dto.AnimeRequest;
import br.com.fiap.animes.Anime.dto.AnimeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("animes")
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeService service;

    @GetMapping
    public List<AnimeResponse> findAll() {
        return service.findAll()
                .stream()
                .map(AnimeResponse::fromEntity)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(AnimeResponse.fromEntity(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<AnimeResponse> create(@RequestBody @Valid AnimeRequest animeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AnimeResponse.fromEntity(service.create(animeRequest.toEntity())));
    }

    @PutMapping("{id}")
    public ResponseEntity<AnimeResponse> update(@PathVariable Long id, @RequestBody @Valid AnimeRequest animeRequest) {
        return ResponseEntity.ok(AnimeResponse.fromEntity(service.update(id, animeRequest.toEntity())));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
