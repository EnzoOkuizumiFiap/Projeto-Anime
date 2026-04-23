package br.com.fiap.animes.Temporada.dto;

import br.com.fiap.animes.Temporada.Temporada;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record TemporadaRequest(
        @NotBlank
        String numTemporada,

        @NotBlank
        Integer qtdEpisodio,

        @NotNull
        @PastOrPresent
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
