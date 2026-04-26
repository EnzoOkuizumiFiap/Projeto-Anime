package br.com.fiap.animes.Personagem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    List<Personagem> findByAnimeId(Long animeId);

    Page<Personagem> findByAnimeId(Long animeId, Pageable pageable);

    @Query("SELECT * FROM Personagem p WHERE p.nome = :name ORDER BY p.nome") //JPQL
    List<Personagem> findByNome(String name);
}
