package br.com.fiap.animes.Anime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Page<AnimeSummary> findByLancamento(LocalDate lancamento, Pageable pageable);

    Page<AnimeSummary> findByLancamentoBetween(Integer releaseYearAfter, Integer releaseYearBefore, Pageable pageable);

    Page<AnimeSummary> findByCategoria(List<Categoria> categorias, Pageable pageable);

    Page<AnimeSummary> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    Optional<Anime> findByTituloIgnoreCase(String titulo);
}
