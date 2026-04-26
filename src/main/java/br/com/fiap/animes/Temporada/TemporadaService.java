package br.com.fiap.animes.Temporada;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.AnimeRepository;
import br.com.fiap.animes.Temporada.dto.TemporadaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TemporadaService {
    private final TemporadaRepository temporadaRepository;
    private final AnimeRepository animeRepository;

    public Page<Temporada> findAll(Pageable pageable) {
        return temporadaRepository.findAll(pageable);
    }

    public Temporada findById(Long id) {
        return findTemporadaById(id);
    }

    public Page<Temporada> findAllByAnimeId(Long animeId, Pageable pageable) {
        if (!animeRepository.existsById(animeId)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + animeId + " não encontrado");

        Page<Temporada> temporadas = temporadaRepository.findByAnimeId(animeId, pageable);

        if (temporadas.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma temporada encontrada para este Anime");

        return temporadas;
    }

    public Temporada create(TemporadaRequest request) {
        Anime animeFound = findAnimeById(request.animeId());
        return temporadaRepository.save(request.toEntity(animeFound));
    }

    public Temporada update(Long id, TemporadaRequest request) {
        findTemporadaById(id);
        Anime animeFound = findAnimeById(request.animeId());
        Temporada temporada = request.toEntity(animeFound);
        temporada.setId(id);
        return temporadaRepository.save(temporada);
    }

    public void delete(Long id) {
        findTemporadaById(id);
        temporadaRepository.deleteById(id);
    }

    private Temporada findTemporadaById(Long id) {
        return temporadaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Temporada com id " + id + " não encontrada"));
    }

    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado"));
    }
}
