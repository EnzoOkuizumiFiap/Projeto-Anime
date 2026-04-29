package br.com.fiap.animes.Anime.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.Categoria;
import br.com.fiap.animes.Personagem.dto.PersonagemResponse;
import br.com.fiap.animes.Temporada.dto.TemporadaResponse;

import java.time.LocalDate;
import java.util.List;

public record AnimeResponse(
    String titulo,
    String descricao,
    LocalDate lancamento,
    List<Categoria> categoria,
    List<PersonagemResponse> personagens,
    List<TemporadaResponse> temporadas
) {
    public static AnimeResponse fromEntity(Anime anime) {
        return new AnimeResponse(
                anime.getTitulo(),
                anime.getDescricao(),
                anime.getLancamento(),
                anime.getCategoria(),
                anime.getPersonagens().stream().map(PersonagemResponse::fromEntity).toList(),
                anime.getTemporadas().stream().map(TemporadaResponse::fromEntity).toList()
        );
    }
}
