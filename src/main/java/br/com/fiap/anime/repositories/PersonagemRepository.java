package br.com.fiap.anime.repositories;

import br.com.fiap.anime.models.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByAnimeId(Long animeId);
}
