package br.com.fiap.animes.Anime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Page<AnimeProjections> findByLancamento(LocalDate lancamento, Pageable pageable);

    Page<Anime> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    boolean existsByTituloIgnoreCase(String titulo);
}
