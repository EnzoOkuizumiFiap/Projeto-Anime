package br.com.fiap.animes.Anime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "animes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    private String descricao;

    @Column(nullable = false)
    private LocalDate lancamento;

    @ElementCollection
    private List<Categoria> categoria;
}
