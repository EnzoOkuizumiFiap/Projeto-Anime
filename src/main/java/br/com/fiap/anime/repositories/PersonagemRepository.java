package br.com.fiap.anime.repositories;

import br.com.fiap.anime.models.Anime;
import br.com.fiap.anime.models.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}
