
package br.com.fiap.animes.Temporada.dto;

import br.com.fiap.animes.validation.UnicaTemporadaPorAnime;
import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Temporada.Temporada;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@UnicaTemporadaPorAnime
public record TemporadaRequest(
        @NotBlank
        @Pattern(regexp = "^Temporada\\s+[1-9]\\d*$", message = "numTemporada deve seguir o padrão 'Temporada X' (ex: Temporada 1)")
        String numTemporada,

        @NotNull
        @Positive
        Integer qtdEpisodio,

        @NotNull
        @PastOrPresent
        LocalDate lancamento,

        @NotNull
        Long animeId
) 
{
    public Temporada toEntity(Anime anime) {
        return Temporada.builder()
                .numTemporada(numTemporada)
                .qtdEpisodio(qtdEpisodio)
                .lancamento(lancamento)
                .anime(anime)
                .build();
    }
}
