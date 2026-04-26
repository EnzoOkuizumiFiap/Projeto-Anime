package br.com.fiap.animes.Temporada;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporadaRepository extends JpaRepository<Temporada, Long> {
    List<Temporada> findByAnimeId(Long animeId);

    Page<Temporada> findByAnimeId(Long animeId, Pageable pageable);
}
