package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Anime.dto.AnimeRequest;
import lombok.RequiredArgsConstructor;
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

    public Page<Anime> findAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public Anime findById(Long id) {
        return findAnimeById(id);
    }

    public Anime create(AnimeRequest request) {
        return animeRepository.save(request.toEntity());
    }

    public Anime update(Long id, AnimeRequest request) {
        findAnimeById(id);
        Anime anime = request.toEntity();
        anime.setId(id);
        return animeRepository.save(anime);
    }

    public void delete(Long id) {
        findAnimeById(id);
        animeRepository.deleteById(id);
    }

    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado." ));
    }

    public Page<Anime> findAllByTitulo(String titulo, Pageable pageable) {
        return animeRepository.findByTituloContainingIgnoreCase(titulo, pageable);
    }

    public Page<AnimeProjections> findAllByLancamento(LocalDate lancamento, Pageable pageable) {
        return animeRepository.findByLancamento(lancamento, pageable);
    }
}
