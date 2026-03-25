package br.com.fiap.anime.controllers;

import br.com.fiap.anime.models.Episodio;
import br.com.fiap.anime.services.EpisodioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("episodios")
public class EpisodioController {
    @Autowired
    private EpisodioService service;

    @GetMapping
    public List<Episodio> ListAll(){
        return service.getAllEpisodios();
    }

    @GetMapping("{id}")
    public ResponseEntity<Episodio> getEpisodioById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEpisodioById(id));
    }

    @PostMapping
    public ResponseEntity<Episodio> createEpisodio(@RequestBody Episodio Episodio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addEpisodio(Episodio));
    }

    @PutMapping("{id}")
    public ResponseEntity<Episodio> updateEpisodio(@PathVariable Long id, @RequestBody Episodio Episodio) {
        return ResponseEntity.ok(service.updateEpisodio(id, Episodio);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEpisodio(@PathVariable Long id) {
        service.deleteEpisodio(id);
        return ResponseEntity.noContent().build();
    }
}
