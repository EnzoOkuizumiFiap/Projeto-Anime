package br.com.fiap.animes.Anime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Page<AnimeProjections> findByReleaseDate(Integer date, Pageable pageable);

    @Query("SELECT * FROM Anime a WHERE a.titulo = :criterio") //JPQL
    List<Anime> findByTitulo(String criterio);
}
