package br.com.fiap.animes.Temporada;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.AnimeRepository;
import br.com.fiap.animes.Temporada.dto.TemporadaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporadaService {
    private final TemporadaRepository temporadaRepository;
    private final AnimeRepository animeRepository;

    public List<Temporada> findAll() {
        return temporadaRepository.findAll();
    }

    public Temporada findById(Long id) {
        return findTemporadaById(id);
    }

    public Temporada create(TemporadaRequest request) {
        Anime anime = findAnimeById(request.animeId());
        return temporadaRepository.save(request.toEntity(anime));
    }

    public Temporada update(Long id, TemporadaRequest request) {
        Temporada newTemporada = findTemporadaById(id);

        Anime anime = findAnimeById(request.animeId());
        newTemporada.setAnime(anime);

        return temporadaRepository.save(newTemporada);
    }

    public void delete(Long id) {
        findTemporadaById(id);
        temporadaRepository.deleteById(id);
    }

    private Temporada findTemporadaById(Long id) {
        return temporadaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Temporada com id " + id + " não encontrada"));
    }

    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado"));
    }
}
