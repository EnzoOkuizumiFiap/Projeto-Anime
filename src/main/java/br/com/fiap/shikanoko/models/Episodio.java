package br.com.fiap.shikanoko.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Episodio {
    private Long id;
    private String title;
    private String description;
    private String duration;
    private List<Personagem> chars;
}
