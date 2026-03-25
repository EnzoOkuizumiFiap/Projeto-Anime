package br.com.fiap.anime.repositories;

import br.com.fiap.anime.models.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
