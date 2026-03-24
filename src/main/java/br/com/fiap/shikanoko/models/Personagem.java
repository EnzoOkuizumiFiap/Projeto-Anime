package br.com.fiap.shikanoko.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Personagem {
    private Long id;
    private String nome;
    private String descricao;
    private List<String> caracteristicas;
    private Boolean isCervo;
}
