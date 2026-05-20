package br.com.fiap.animes.data;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.AnimeService;
import br.com.fiap.animes.Anime.Categoria;
import br.com.fiap.animes.Anime.dto.AnimeRequest;
import br.com.fiap.animes.Personagem.PersonagemService;
import br.com.fiap.animes.Personagem.dto.PersonagemRequest;
import br.com.fiap.animes.Temporada.TemporadaService;
import br.com.fiap.animes.Temporada.dto.TemporadaRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MockData {

    private final AnimeService animeService;
    private final PersonagemService personagemService;
    private final TemporadaService temporadaService;

    @PostConstruct
    public void init() {

        Anime naruto = animeService.create(
                new AnimeRequest(
                        "Naruto",
                        "Jovem ninja que busca reconhecimento e sonha em se tornar Hokage.",
                        LocalDate.of(2002, 10, 3),
                        List.of(Categoria.ACAO, Categoria.AVENTURA),
                        List.of(),
                        List.of()
                )
        );

        Anime attackOnTitan = animeService.create(
                new AnimeRequest(
                        "Attack on Titan",
                        "Humanidade luta contra titãs gigantes que ameaçam sua existência.",
                        LocalDate.of(2013, 4, 7),
                        List.of(Categoria.ACAO, Categoria.DRAMA, Categoria.SUSPENSE),
                        List.of(),
                        List.of()
                )
        );

        Anime deathNote = animeService.create(
                new AnimeRequest(
                        "Death Note",
                        "Estudante encontra um caderno capaz de matar qualquer pessoa.",
                        LocalDate.of(2006, 10, 4),
                        List.of(Categoria.MISTERIO, Categoria.SUSPENSE, Categoria.DRAMA),
                        List.of(),
                        List.of()
                )
        );

        Anime onePiece = animeService.create(
                new AnimeRequest(
                        "One Piece",
                        "Piratas exploram os mares em busca do lendário tesouro One Piece.",
                        LocalDate.of(1999, 10, 20),
                        List.of(Categoria.AVENTURA, Categoria.COMEDIA),
                        List.of(),
                        List.of()
                )
        );

        Anime fmab = animeService.create(
                new AnimeRequest(
                        "Fullmetal Alchemist Brotherhood",
                        "Dois irmãos alquimistas buscam recuperar seus corpos.",
                        LocalDate.of(2009, 4, 5),
                        List.of(Categoria.ACAO, Categoria.FANTASIA),
                        List.of(),
                        List.of()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "Naruto Uzumaki",
                        "Determinado e impulsivo",
                        naruto.getId()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "Sasuke Uchiha",
                        "Frio e estrategista",
                        naruto.getId()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "Eren Yeager",
                        "Explosivo e vingativo",
                        attackOnTitan.getId()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "Levi Ackerman",
                        "Calmo e extremamente habilidoso",
                        attackOnTitan.getId()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "Light Yagami",
                        "Inteligente e manipulador",
                        deathNote.getId()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "L",
                        "Excêntrico e genial",
                        deathNote.getId()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "Monkey D. Luffy",
                        "Alegre e corajoso",
                        onePiece.getId()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "Roronoa Zoro",
                        "Leal e disciplinado",
                        onePiece.getId()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "Edward Elric",
                        "Impulsivo e brilhante",
                        fmab.getId()
                )
        );

        personagemService.create(
                new PersonagemRequest(
                        "Alphonse Elric",
                        "Gentil e protetor",
                        fmab.getId()
                )
        );

        temporadaService.create(
                new TemporadaRequest(
                        "1",
                        220,
                        LocalDate.of(2002, 10, 3),
                        naruto.getId()
                )
        );

        temporadaService.create(
                new TemporadaRequest(
                        "1",
                        25,
                        LocalDate.of(2013, 4, 7),
                        attackOnTitan.getId()
                )
        );

        temporadaService.create(
                new TemporadaRequest(
                        "1",
                        37,
                        LocalDate.of(2006, 10, 4),
                        deathNote.getId()
                )
        );

        temporadaService.create(
                new TemporadaRequest(
                        "1",
                        61,
                        LocalDate.of(1999, 10, 20),
                        onePiece.getId()
                )
        );

        temporadaService.create(
                new TemporadaRequest(
                        "1",
                        64,
                        LocalDate.of(2009, 4, 5),
                        fmab.getId()
                )
        );
    }
}
