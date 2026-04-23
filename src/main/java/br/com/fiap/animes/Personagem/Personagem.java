package br.com.fiap.animes.Personagem;

import br.com.fiap.animes.Anime.Anime;
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
    private String personalidade;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;
}
