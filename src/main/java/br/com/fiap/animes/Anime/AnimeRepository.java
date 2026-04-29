package br.com.fiap.animes.Anime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Page<AnimeSummary> findDistinctByLancamento(LocalDate lancamento, Pageable pageable);

    Page<AnimeSummary> findDistinctByLancamentoBetween(LocalDate releaseAfter, LocalDate releaseBefore, Pageable pageable);

    @Query("SELECT a FROM Anime a JOIN a.categoria c WHERE c IN :categorias")
    Page<AnimeSummary> findByCategorias(List<Categoria> categorias, Pageable pageable);

    List<AnimeSummary> findByTituloContainingIgnoreCase(String titulo);

    Optional<Anime> findByTituloIgnoreCase(String titulo);
}