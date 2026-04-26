package br.com.fiap.animes.Anime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Page<AnimeProjections> findByLancamento(LocalDate lancamento, Pageable pageable);

    List<Anime> findByTitulo(String titulo);
}
