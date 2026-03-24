package br.com.fiap.shikanoko.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Personagem {
    private Long id;
    private String nome;
    private String description;
    private Boolean isDeer;
}
