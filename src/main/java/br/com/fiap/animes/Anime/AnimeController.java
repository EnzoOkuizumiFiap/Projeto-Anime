package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Anime.dto.AnimeRequest;
import br.com.fiap.animes.Anime.dto.AnimeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("animes")
@RequiredArgsConstructor
public class AnimeController {
    private final AnimeService animeService;

    @GetMapping
    public List<AnimeResponse> getAllAnimes(){
        return animeService.findAll()
                .stream()
                .map(AnimeResponse::fromEntity)
                .toList();
    }

    @GetMapping("{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        return ResponseEntity.ok(animeService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anime create(@RequestBody AnimeRequest animeRequest) {
        return animeService.create(animeRequest.toEntity());
    }

    @PutMapping("{id}")
    public Anime update(@PathVariable Long id, @RequestBody Anime anime) {
        return animeService.update(id, anime);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnime(@PathVariable Long id) {
        animeService.delete(id);
    }
}
