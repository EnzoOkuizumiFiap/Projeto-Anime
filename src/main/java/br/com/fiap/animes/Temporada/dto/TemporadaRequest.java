package br.com.fiap.animes.Temporada.dto;

import br.com.fiap.animes.Temporada.Temporada;

import java.time.LocalDate;

public record TemporadaRequest(
        String numTemporada,
        Integer qtdEpisodio,
        LocalDate lancamento
)
{
    public Temporada toEntity()
    {
        return Temporada.builder()
                .numTemporada(numTemporada)
                .qtdEpisodio(qtdEpisodio)
                .lancamento(lancamento)
                .build();
    }
}
