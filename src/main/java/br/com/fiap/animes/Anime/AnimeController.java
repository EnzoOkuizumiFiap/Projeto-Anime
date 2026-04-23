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
    public List<AnimeResponse> findAllAnimes(){
        return animeService.findAll()
                .stream()
                .map(AnimeResponse::fromEntity)
                .toList();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Anime findAnimeById(@PathVariable Long id) {
        return animeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anime create(@RequestBody AnimeRequest animeRequest) {
        return animeService.create(animeRequest.toEntity());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Anime update(@PathVariable Long id, @RequestBody AnimeRequest animeRequest) {
        return animeService.update(id, animeRequest.toEntity());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnime(@PathVariable Long id) {
        animeService.delete(id);
    }
}
