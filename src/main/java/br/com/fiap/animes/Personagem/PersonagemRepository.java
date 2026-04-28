package br.com.fiap.animes.Personagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByAnimeId(Long animeId);

    Page<Personagem> findByAnimeId(Long animeId, Pageable pageable);

    Page<PersonagemSummary> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
