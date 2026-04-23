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

    private String numTemporada;

    private Integer qtdEpisodio;

    private LocalDate lancamento;

    @ManyToOne
    @JoinColumn(name = "anime_id")
    private Anime anime;
}
