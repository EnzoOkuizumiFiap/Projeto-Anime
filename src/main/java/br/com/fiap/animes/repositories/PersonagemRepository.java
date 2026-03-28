package br.com.fiap.animes.repositories;

import br.com.fiap.animes.models.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByAnimeId(Long animeId);
}
