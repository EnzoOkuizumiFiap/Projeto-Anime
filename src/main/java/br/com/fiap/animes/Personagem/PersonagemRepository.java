package br.com.fiap.animes.Personagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    Page<Personagem> findByAnimeId(Long animeId, Pageable pageable);

    List<PersonagemSummary> findByNomeContainingIgnoreCase(String nome);

    Optional<Personagem> findByNomeIgnoreCase(String nome);
}
