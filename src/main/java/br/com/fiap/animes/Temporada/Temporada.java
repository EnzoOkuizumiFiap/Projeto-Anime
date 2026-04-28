package br.com.fiap.animes.Temporada;

import br.com.fiap.animes.Anime.Anime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "temporadas")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Temporada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String numTemporada;

    @Column(nullable = false)
    private Integer qtdEpisodio;

    @Column(nullable = false)
    private LocalDate lancamento;

    @ManyToOne(optional = false)
    private Anime anime;
}
