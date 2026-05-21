package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Anime.dto.AnimeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;

    @Cacheable("animes")
    public Page<Anime> findAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    @Cacheable(value = "animes", key = "'by-title/' + #titulo")
    public Page<AnimeSummary> findAllByTituloContaining(String titulo, Pageable pageable) {
        return animeRepository.findByTituloContainingIgnoreCase(titulo, pageable);
    }

    @Cacheable(value = "animes", key = "'by-category/' + #categorias")
    public Page<AnimeSummary> findAllByCategoria(List<Categoria> categorias, Pageable pageable) {
        return animeRepository.findByCategorias(categorias, pageable);
    }

    @Cacheable(value = "animes", key = "'by-date/' + #lancamento")
    public Page<AnimeSummary> findAllByLancamento(LocalDate lancamento, Pageable pageable) {
        return animeRepository.findDistinctByLancamento(lancamento, pageable);
    }

    @Cacheable(value = "animes", key = "'by-year-range/' + #from + '/' + #to")
    public Page<AnimeSummary> findAllByPeriodoLancamento(LocalDate from, LocalDate to, Pageable pageable) {
        return animeRepository.findDistinctByLancamentoBetween(from, to, pageable);
    }

    public Anime findById(Long id) {
        return findAnimeById(id);
    }

    @CacheEvict(value = "animes", allEntries = true)
    public Anime create(AnimeRequest request) {
        return animeRepository.save(request.toEntity());
    }

    @CacheEvict(value = "animes", allEntries = true)
    public Anime update(Long id, AnimeRequest request) {
        findAnimeById(id);
        Anime anime = request.toEntity();
        anime.setId(id);
        return animeRepository.save(anime);
    }

    @CacheEvict(value = "animes", allEntries = true)
    public void delete(Long id) {
        findAnimeById(id);
        animeRepository.deleteById(id);
    }

    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado." ));
    }
}
