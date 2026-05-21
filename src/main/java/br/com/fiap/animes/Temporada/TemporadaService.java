package br.com.fiap.animes.Temporada;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.AnimeRepository;
import br.com.fiap.animes.Temporada.dto.TemporadaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("temporadas")
    public Page<Temporada> findAll(Pageable pageable) {
        return temporadaRepository.findAll(pageable);
    }

    @Cacheable(value = "temporadas", key = "#id")
    public Temporada findById(Long id) {
        return findTemporadaById(id);
    }

    @Cacheable(value = "temporadas", key = "'by-anime/' + #animeId")
    public Page<Temporada> findAllByAnimeId(Long animeId, Pageable pageable) {
        findAnimeById(animeId);
        return temporadaRepository.findByAnimeId(animeId, pageable);
    }

    @CacheEvict(value = "temporadas", allEntries = true)
    public Temporada create(TemporadaRequest request) {
        Anime animeFound = findAnimeById(request.animeId());
        return temporadaRepository.save(request.toEntity(animeFound));
    }

    @CacheEvict(value = "temporadas", allEntries = true)
    public Temporada update(Long id, TemporadaRequest request) {
        findTemporadaById(id);

        Anime animeFound = findAnimeById(request.animeId());
        Temporada temporada = request.toEntity(animeFound);
        temporada.setId(id);

        return temporadaRepository.save(temporada);
    }

    @CacheEvict(value = "temporadas", allEntries = true)
    public void delete(Long id) {
        findTemporadaById(id);
        temporadaRepository.deleteById(id);
    }

    @Cacheable
    private Temporada findTemporadaById(Long id) {
        return temporadaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Temporada com id " + id + " não encontrada"));
    }

    @Cacheable
    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado"));
    }
}