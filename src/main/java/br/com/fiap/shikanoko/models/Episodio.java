package br.com.fiap.shikanoko.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Episodio {
    private Long id;
    private String title;
    private String descricao;
}
