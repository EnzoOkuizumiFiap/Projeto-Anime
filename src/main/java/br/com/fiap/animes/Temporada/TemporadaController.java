package br.com.fiap.animes.Temporada;

import br.com.fiap.animes.Temporada.dto.TemporadaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temporadas")
@RequiredArgsConstructor
public class TemporadaController {

    private TemporadaService temporadaService;

    @GetMapping
    public List<TemporadaResponse> findAll() {
        return temporadaService.findAll()
                .stream()
                .map(TemporadaResponse::fromEntity)
                .toList();
    }

    @GetMapping
    public List<TemporadaResponse> findAll() {
        return temporadaService.findAll()
                .stream()
                .map(TemporadaResponse::fromEntity)
                .toList();
    }

    @PostMapping
    public Temporada create(@RequestBody Temporada temporada) {
        return temporadaService.create(temporada);
    }

}
