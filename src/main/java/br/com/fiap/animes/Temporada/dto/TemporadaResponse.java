package br.com.fiap.animes.Temporada.dto;

import br.com.fiap.animes.Temporada.Temporada;

import java.time.LocalDate;

public record TemporadaResponse(
        String numTemporada,
        Integer qtdEpisodio,
        LocalDate lancamento,
        Long animeId
) {
    public static TemporadaResponse fromEntity(Temporada temporada) {
        return new TemporadaResponse(
                temporada.getNumTemporada(),
                temporada.getQtdEpisodio(),
                temporada.getLancamento(),
                temporada.getAnime().getId()
        );
    }
}
