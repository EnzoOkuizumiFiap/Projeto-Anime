package br.com.fiap.anime.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Episodio {
    private Long id;
    private String titulo;
    private String descricao;
    private String duracao;
    private List<Personagem> personagens;
}
