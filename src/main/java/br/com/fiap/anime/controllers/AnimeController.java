package br.com.fiap.anime.controllers;

import br.com.fiap.anime.models.Anime;
import br.com.fiap.anime.services.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("animes")
public class AnimeController {
    @Autowired
    private AnimeService service;

    @GetMapping
    public List<Anime> ListAll(){
        return service.getAllAnimes();
    }

    @GetMapping("{id}")
    public ResponseEntity<Anime> getAnimeById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAnimeById(id));
    }

    @PostMapping
    public ResponseEntity<Anime> createAnime(@RequestBody Anime anime) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addAnime(anime));
    }

    @PutMapping("{id}")
    public ResponseEntity<Anime> updateAnime(@PathVariable Long id, @RequestBody Anime anime) {
        return ResponseEntity.ok(service.updateAnime(id, Anime);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAnime(@PathVariable Long id) {
        service.deleteAnime(id);
        return ResponseEntity.noContent().build();
    }
}
