package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Personagem.Personagem;
import br.com.fiap.animes.Temporada.Temporada;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL)
    private List<Personagem> personagens;

    @OneToMany(mappedBy = "anime", cascade = CascadeType.ALL)
    private List<Temporada> temporadas;

//    public EntityModel<Anime> toEntityModel(){
//        var linkAllAnimes = linkTo(methodOn(AnimeController.class).findAll(null, null)).withRel("all-animes").withTitle("All animes");
//        var linkSelf = linkTo(methodOn(AnimeController.class).findById(id)).withSelfRel().withTitle("Anime details");
//        var linkTitle = linkTo(methodOn(AnimeController.class).findAllByTitle(titulo, null, null)).withRel("close-title").withTitle("Close titles");
//
//        return EntityModel.of(this, linkSelf, linkAllAnimes, linkTitle);
//    }
}
