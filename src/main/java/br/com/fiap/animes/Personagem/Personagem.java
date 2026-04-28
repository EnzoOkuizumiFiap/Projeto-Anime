package br.com.fiap.animes.Personagem;

import br.com.fiap.animes.Anime.Anime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "personagens")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String personalidade;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Anime anime;
}
