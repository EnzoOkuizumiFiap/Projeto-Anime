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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.fiap.animes.Anime.Categoria.*;

@Configuration
@RequiredArgsConstructor
public class MockData {

    private final AnimeService animeService;
    private final PersonagemService personagemService;
    private final TemporadaService temporadaService;

    private final Map<String, Anime> animes = new HashMap<>();

    private Anime anime(String titulo, String descricao, LocalDate lancamento, Categoria... categorias) {
        Anime a = animeService.create(new AnimeRequest(titulo, descricao, lancamento, List.of(categorias), List.of(), List.of()));
        animes.put(titulo, a);
        return a;
    }

    private void personagem(String nome, String personalidade, String animeTitulo) {
        personagemService.create(new PersonagemRequest(nome, personalidade, animes.get(animeTitulo).getId()));
    }

    private void temporada(String animeTitulo, String num, int eps, LocalDate lancamento) {
        temporadaService.create(new TemporadaRequest(num, eps, lancamento, animes.get(animeTitulo).getId()));
    }

    @PostConstruct
    public void init() {
        criarAnimes();
        criarPersonagens();
        criarTemporadas();
    }

    private void criarAnimes() {
        anime("Shikimoris Not Just a Cutie", "Um anime de comédia romântica", LocalDate.of(2022, 4, 9), ROMANCE, COMEDIA);
        anime("Mardock Scramble", "Um anime Sci-Fi Cyberpunk Neo-noir de 2010", LocalDate.of(2010, 11, 6), SCIFI, CYBERPUNK, DRAMA);
        anime("Bocchi The Rock", "A vida desta estudante tímida e apaixonada por guitarra mudará da água pro vinho após entrar numa banda", LocalDate.of(2022, 10, 9), COMEDIA, SLICEOFLIFE, MUSICAL);
        anime("Steins Gate", "Um anime Sci-Fi sobre viagens no tempo e consequências imprevisíveis", LocalDate.of(2011, 4, 6), SCIFI, DRAMA);
        anime("Your Lie in April", "Um drama musical emocionante sobre superação e amor", LocalDate.of(2014, 10, 9), DRAMA, ROMANCE, MUSICAL);
        anime("Chainsaw Man", "Um anime de ação sobrenatural com elementos de horror e comédia", LocalDate.of(2022, 10, 12), ACAO, TERROR, COMEDIA);
        anime("Attack on Titan", "Humanidade luta contra titãs gigantes em uma trama cheia de mistério e ação", LocalDate.of(2013, 4, 7), ACAO, DRAMA, SUSPENSE);
        anime("Vivy Fluorite Eyes Song", "Uma IA cantora que precisa salvar o futuro da humanidade", LocalDate.of(2021, 4, 3), SCIFI, DRAMA, MUSICAL);
        anime("Death Note", "Um estudante encontra um caderno que mata qualquer pessoa cujo nome seja escrito nele", LocalDate.of(2006, 10, 4), SUSPENSE, DRAMA);
        anime("Fullmetal Alchemist Brotherhood", "Dois irmãos alquimistas buscam recuperar seus corpos após um experimento fracassado", LocalDate.of(2009, 4, 5), ACAO, DRAMA);
        anime("Demon Slayer", "Um jovem se torna caçador de demônios para salvar sua irmã", LocalDate.of(2019, 4, 6), ACAO);
        anime("Jujutsu Kaisen", "Estudantes enfrentam maldições usando energia amaldiçoada", LocalDate.of(2020, 10, 3), ACAO);
        anime("Naruto", "Um ninja busca reconhecimento e sonha em se tornar Hokage", LocalDate.of(2002, 10, 3), ACAO);
        anime("Naruto Shippuden", "Continuação da jornada de Naruto com ameaças maiores", LocalDate.of(2007, 2, 15), ACAO);
        anime("One Piece", "Um jovem pirata busca o maior tesouro do mundo", LocalDate.of(1999, 10, 20), AVENTURA);
        anime("Tokyo Ghoul", "Um estudante se torna meio-ghoul após um acidente", LocalDate.of(2014, 7, 4), TERROR, DRAMA);
        anime("Code Geass", "Um jovem ganha o poder de controlar pessoas e inicia uma revolução", LocalDate.of(2006, 10, 6), SCIFI, ACAO);
        anime("Re Zero", "Um garoto revive sempre que morre em outro mundo", LocalDate.of(2016, 4, 4), FANTASIA, DRAMA);
        anime("No Game No Life", "Dois irmãos gênios dos jogos vão para um mundo onde tudo é decidido por jogos", LocalDate.of(2014, 4, 9), FANTASIA, COMEDIA);
        anime("Sword Art Online", "Jogadores ficam presos em um jogo de realidade virtual mortal", LocalDate.of(2012, 7, 8), ACAO, FANTASIA);
        anime("Mob Psycho 100", "Um garoto psíquico tenta viver uma vida normal", LocalDate.of(2016, 7, 11), COMEDIA);
        anime("One Punch Man", "Um herói derrota qualquer inimigo com um único soco", LocalDate.of(2015, 10, 5), COMEDIA, ACAO);
        anime("Haikyuu", "Um time de vôlei busca o topo do campeonato escolar", LocalDate.of(2014, 4, 6), ESPORTE);
        anime("Kuroko no Basket", "Jogadores talentosos competem no basquete escolar", LocalDate.of(2012, 4, 8), ESPORTE);
        anime("Dr Stone", "Um cientista reconstrói a civilização após a humanidade virar pedra", LocalDate.of(2019, 7, 5), SCIFI);
        anime("Vinland Saga", "Uma história de vingança e guerra na era dos vikings", LocalDate.of(2019, 7, 7), ACAO, DRAMA);
        anime("Erased", "Um homem volta no tempo para impedir tragédias", LocalDate.of(2016, 1, 8), SUSPENSE);
        anime("Parasyte", "Seres alienígenas invadem corpos humanos", LocalDate.of(2014, 10, 9), TERROR);
        anime("Made in Abyss", "Uma garota explora um abismo cheio de mistérios", LocalDate.of(2017, 7, 7), AVENTURA, DRAMA);
        anime("Clannad", "Um estudante encontra propósito através de novas relações", LocalDate.of(2007, 10, 5), DRAMA, ROMANCE);
        anime("Clannad After Story", "Continuação focada na vida adulta e desafios emocionais", LocalDate.of(2008, 10, 3), DRAMA);
        anime("Angel Beats", "Almas lidam com arrependimentos após a morte", LocalDate.of(2010, 4, 3), DRAMA);
        anime("Anohana", "Amigos lidam com a perda de uma amiga", LocalDate.of(2011, 4, 15), DRAMA);
        anime("Toradora", "Dois estudantes ajudam um ao outro em seus romances", LocalDate.of(2008, 10, 2), ROMANCE, COMEDIA);
        anime("Horimiya", "Um romance escolar realista e emocional", LocalDate.of(2021, 1, 10), ROMANCE);
        anime("Oregairu", "Um estudante cínico aprende sobre relacionamentos", LocalDate.of(2013, 4, 5), ROMANCE, DRAMA);
        anime("The Promised Neverland", "Crianças descobrem um segredo sombrio no orfanato", LocalDate.of(2019, 1, 11), SUSPENSE);
        anime("Blue Lock", "Jogadores competem para se tornar o melhor atacante do Japão", LocalDate.of(2022, 10, 9), ESPORTE);
        anime("Spy x Family", "Uma família falsa com segredos perigosos", LocalDate.of(2022, 4, 9), COMEDIA, ACAO);
        anime("Akame ga Kill", "Um jovem entra para um grupo de assassinos revolucionários", LocalDate.of(2014, 7, 7), ACAO);
        anime("Black Clover", "Um garoto sem magia busca se tornar o rei mago", LocalDate.of(2017, 10, 3), ACAO, FANTASIA);
        anime("Fairy Tail", "Magos vivem aventuras em uma guilda", LocalDate.of(2009, 10, 12), AVENTURA);
        anime("Guilty Crown", "Um jovem ganha poderes em um mundo distópico", LocalDate.of(2011, 10, 14), SCIFI);
        anime("Zankyou no Terror", "Dois jovens terroristas desafiam o sistema", LocalDate.of(2014, 7, 11), SUSPENSE);
        anime("Another", "Uma maldição mortal assombra uma turma escolar", LocalDate.of(2012, 1, 10), TERROR);
        anime("Mirai Nikki", "Um jogo mortal entre usuários de diários do futuro", LocalDate.of(2011, 10, 9), SUSPENSE);
        anime("Darling in the Franxx", "Jovens pilotam robôs em um mundo pós-apocalíptico", LocalDate.of(2018, 1, 13), SCIFI, ROMANCE);
        anime("86", "Soldados lutam em uma guerra enquanto são discriminados", LocalDate.of(2021, 4, 11), ACAO, DRAMA);
        anime("Tengoku Daimakyou", "Crianças vivem em um mundo isolado enquanto exploradores buscam respostas", LocalDate.of(2023, 4, 1), SCIFI, MISTERIO);
        anime("Cyberpunk Edgerunners", "Um jovem tenta sobreviver em uma cidade dominada por tecnologia e crime", LocalDate.of(2022, 9, 13), CYBERPUNK, ACAO);
    }

    private void criarPersonagens() {
        personagem("Micchon Shikimori", "Shikimori é uma garota gentil com um lindo sorriso", "Shikimoris Not Just a Cutie");
        personagem("Yuu Izumi", "Izumi é um cara muito bondoso doce gentil desastrado e tímido", "Shikimoris Not Just a Cutie");
        personagem("Rune Balot", "Rune Balot é uma garota vulnerável desconfiada mas forte e determinada", "Mardock Scramble");
        personagem("Hitori Gotoh", "Hitori Gotoh é uma garota extremamente tímida e introvertida com um forte desejo de se apresentar em uma banda", "Bocchi The Rock");
        personagem("Rintarou Okabe", "Um cientista excêntrico e carismático obcecado por teorias de conspiração e viagens no tempo", "Steins Gate");
        personagem("Kaori Miyazono", "Uma violinista alegre e inspiradora que transforma a vida de todos ao seu redor", "Your Lie in April");
        personagem("Kousei Arima", "Um pianista prodígio que luta contra traumas do passado e busca reencontrar sua paixão pela música", "Your Lie in April");
        personagem("Denji", "Um jovem pobre que se torna caçador de demônios após se fundir com seu cachorro-serra Pochita", "Chainsaw Man");
        personagem("Eren Yeager", "Um jovem determinado que deseja exterminar todos os titãs e descobrir a verdade sobre o mundo", "Attack on Titan");
        personagem("Vivy", "Uma IA cantora com personalidade gentil e determinada a cumprir sua missão de salvar a humanidade", "Vivy Fluorite Eyes Song");
        personagem("Light Yagami", "Extremamente inteligente e manipulador", "Death Note");
        personagem("L", "Detetive excêntrico e genial", "Death Note");
        personagem("Tanjiro Kamado", "Gentil e determinado", "Demon Slayer");
        personagem("Gojo Satoru", "Confiante e extremamente poderoso", "Jujutsu Kaisen");
        personagem("Naruto Uzumaki", "Persistente e otimista", "Naruto");
        personagem("Monkey D Luffy", "Alegre e determinado", "One Piece");
        personagem("Kaneki Ken", "Conflituoso e introspectivo", "Tokyo Ghoul");
        personagem("Lelouch Lamperouge", "Estratégico e ambicioso", "Code Geass");
        personagem("Subaru Natsuki", "Persistente apesar do sofrimento", "Re Zero");
        personagem("Saitama", "Apático e invencível", "One Punch Man");
    }

    private void criarTemporadas() {
        temporada("Shikimoris Not Just a Cutie", "Temporada 1", 12, LocalDate.of(2022, 4, 9));
        temporada("Mardock Scramble", "Temporada 1", 3, LocalDate.of(2010, 11, 6));
        temporada("Bocchi The Rock", "Temporada 1", 12, LocalDate.of(2022, 10, 9));
        temporada("Steins Gate", "Temporada 1", 24, LocalDate.of(2011, 4, 6));
        temporada("Your Lie in April", "Temporada 1", 22, LocalDate.of(2014, 10, 9));
        temporada("Chainsaw Man", "Temporada 1", 12, LocalDate.of(2022, 10, 12));
        temporada("Attack on Titan", "Temporada 1", 25, LocalDate.of(2013, 4, 7));
        temporada("Attack on Titan", "Temporada 2", 12, LocalDate.of(2014, 7, 5));
        temporada("Vivy Fluorite Eyes Song", "Temporada 1", 13, LocalDate.of(2021, 4, 3));
        temporada("Fullmetal Alchemist Brotherhood", "Temporada 1", 64, LocalDate.of(2009, 4, 5));
        temporada("Demon Slayer", "Temporada 1", 26, LocalDate.of(2019, 4, 6));
        temporada("Jujutsu Kaisen", "Temporada 1", 24, LocalDate.of(2020, 10, 3));
        temporada("Naruto", "Temporada 1", 220, LocalDate.of(2002, 10, 3));
        temporada("Naruto Shippuden", "Temporada 1", 500, LocalDate.of(2007, 2, 15));
        temporada("One Piece", "Temporada 1", 1000, LocalDate.of(1999, 10, 20));
        temporada("Tokyo Ghoul", "Temporada 1", 12, LocalDate.of(2014, 7, 4));
        temporada("Tokyo Ghoul", "Temporada 2", 12, LocalDate.of(2015, 1, 9));
        temporada("Code Geass", "Temporada 1", 25, LocalDate.of(2006, 10, 6));
        temporada("Code Geass", "Temporada 2", 25, LocalDate.of(2008, 4, 6));
        temporada("Re Zero", "Temporada 1", 25, LocalDate.of(2016, 4, 4));
        temporada("Re Zero", "Temporada 2", 25, LocalDate.of(2020, 7, 8));
        temporada("No Game No Life", "Temporada 1", 12, LocalDate.of(2014, 4, 9));
        temporada("Sword Art Online", "Temporada 1", 25, LocalDate.of(2012, 7, 8));
        temporada("Sword Art Online", "Temporada 2", 24, LocalDate.of(2014, 7, 5));
        temporada("Mob Psycho 100", "Temporada 1", 12, LocalDate.of(2016, 7, 11));
        temporada("Mob Psycho 100", "Temporada 2", 13, LocalDate.of(2019, 1, 7));
        temporada("One Punch Man", "Temporada 1", 12, LocalDate.of(2015, 10, 5));
        temporada("One Punch Man", "Temporada 2", 12, LocalDate.of(2019, 4, 10));
        temporada("Haikyuu", "Temporada 1", 25, LocalDate.of(2014, 4, 6));
        temporada("Haikyuu", "Temporada 2", 25, LocalDate.of(2015, 10, 3));
        temporada("Kuroko no Basket", "Temporada 1", 25, LocalDate.of(2012, 4, 8));
        temporada("Kuroko no Basket", "Temporada 2", 25, LocalDate.of(2013, 10, 5));
        temporada("Dr Stone", "Temporada 1", 24, LocalDate.of(2019, 7, 5));
        temporada("Vinland Saga", "Temporada 1", 24, LocalDate.of(2019, 7, 7));
        temporada("Erased", "Temporada 1", 12, LocalDate.of(2016, 1, 8));
        temporada("Parasyte", "Temporada 1", 24, LocalDate.of(2014, 10, 9));
        temporada("Made in Abyss", "Temporada 1", 13, LocalDate.of(2017, 7, 7));
        temporada("Clannad", "Temporada 1", 23, LocalDate.of(2007, 10, 5));
        temporada("Clannad After Story", "Temporada 1", 24, LocalDate.of(2008, 10, 3));
        temporada("Angel Beats", "Temporada 1", 13, LocalDate.of(2010, 4, 3));
        temporada("Anohana", "Temporada 1", 11, LocalDate.of(2011, 4, 15));
        temporada("Toradora", "Temporada 1", 25, LocalDate.of(2008, 10, 2));
        temporada("Horimiya", "Temporada 1", 13, LocalDate.of(2021, 1, 10));
        temporada("Oregairu", "Temporada 1", 13, LocalDate.of(2013, 4, 5));
        temporada("Oregairu", "Temporada 2", 13, LocalDate.of(2015, 4, 2));
        temporada("The Promised Neverland", "Temporada 1", 12, LocalDate.of(2019, 1, 11));
        temporada("The Promised Neverland", "Temporada 2", 11, LocalDate.of(2021, 1, 8));
        temporada("Blue Lock", "Temporada 1", 24, LocalDate.of(2022, 10, 9));
        temporada("Spy x Family", "Temporada 1", 12, LocalDate.of(2022, 4, 9));
        temporada("Spy x Family", "Temporada 2", 12, LocalDate.of(2022, 10, 1));
        temporada("Akame ga Kill", "Temporada 1", 24, LocalDate.of(2014, 7, 7));
        temporada("Black Clover", "Temporada 1", 170, LocalDate.of(2017, 10, 3));
        temporada("Fairy Tail", "Temporada 1", 175, LocalDate.of(2009, 10, 12));
        temporada("Guilty Crown", "Temporada 1", 22, LocalDate.of(2011, 10, 14));
        temporada("Zankyou no Terror", "Temporada 1", 11, LocalDate.of(2014, 7, 11));
        temporada("Another", "Temporada 1", 12, LocalDate.of(2012, 1, 10));
        temporada("Mirai Nikki", "Temporada 1", 26, LocalDate.of(2011, 10, 9));
        temporada("Darling in the Franxx", "Temporada 1", 24, LocalDate.of(2018, 1, 13));
        temporada("86", "Temporada 1", 11, LocalDate.of(2021, 4, 11));
        temporada("86", "Temporada 2", 12, LocalDate.of(2021, 10, 3));
        temporada("Tengoku Daimakyou", "Temporada 1", 13, LocalDate.of(2023, 4, 1));
        temporada("Cyberpunk Edgerunners", "Temporada 1", 10, LocalDate.of(2022, 9, 13));
    }
}
