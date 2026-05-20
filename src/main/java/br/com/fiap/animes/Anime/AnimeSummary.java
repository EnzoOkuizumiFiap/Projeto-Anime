package br.com.fiap.animes.Anime;

import java.time.LocalDate;
import java.util.List;

public interface AnimeSummary {
    Long getId();
    String getTitulo();
    LocalDate getLancamento();
    List<Categoria> getCategoria();
}
