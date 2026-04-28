package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Personagem.Personagem;
import br.com.fiap.animes.Temporada.Temporada;
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

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate lancamento;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<Categoria> categoria;

    @OneToMany(mappedBy = "anime")
    private List<Personagem> personagens;

    @OneToMany(mappedBy = "anime")
    private List<Temporada> temporadas;
}
