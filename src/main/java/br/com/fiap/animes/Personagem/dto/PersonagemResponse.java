package br.com.fiap.animes.Personagem.dto;

import br.com.fiap.animes.Personagem.Personagem;

public record PersonagemResponse (
        String nome,
        String personalidade,
        Long animeId
) {
    public static PersonagemResponse fromEntity(Personagem personagem) {
        return new PersonagemResponse(
                personagem.getNome(),
                personagem.getPersonalidade(),
                personagem.getAnime().getId()
        );
    }
}
