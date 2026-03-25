package br.com.fiap.anime.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class Anime {
    private Long id;
    private String titulo;
    private String descricao;
    private List<Episodio> episodios;
    private LocalDate lancamento;
    private String categoria;
    private String diretor;
}
