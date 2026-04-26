package br.com.fiap.animes.Anime;

import java.time.LocalDate;

public interface AnimeProjections {
    Long getId();
    String getTitulo();
    LocalDate getLancamento();
}
