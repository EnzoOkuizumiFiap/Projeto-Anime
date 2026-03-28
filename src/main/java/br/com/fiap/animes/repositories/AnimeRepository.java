package br.com.fiap.animes.repositories;

import br.com.fiap.animes.models.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
