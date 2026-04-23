package br.com.fiap.animes.Personagem.dto;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Personagem.Personagem;

public record PersonagemRequest (
        String nome,
        String personalidade,
        Long animeId
) {
    public Personagem toEntity(Anime anime) {
        return Personagem.builder()
                .nome(nome)
                .personalidade(personalidade)
                .anime(anime)
                .build();
    }
}
