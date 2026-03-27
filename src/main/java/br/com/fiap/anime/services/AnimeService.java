package br.com.fiap.anime.services;

import br.com.fiap.anime.models.Anime;
import br.com.fiap.anime.models.Personagem;
import br.com.fiap.anime.repositories.AnimeRepository;
import br.com.fiap.anime.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private PersonagemRepository personagemRepository;

    public List<Anime> getAllAnimes() {
        return animeRepository.findAll();
    }

    public Anime getAnimeById(Long id) {
        return findAnimeById(id);
    }

    public Anime addAnime(Anime anime) {
        return animeRepository.save(anime);
    }

    public Anime updateAnime(Long id, Anime newAnime) {
        newAnime.setId(findAnimeById(id).getId());
        return animeRepository.save(newAnime);
    }

    public void deleteAnime(Long id) {
        findAnimeById(id);
        List<Personagem> personagens = personagemRepository.findByAnimeId(id);
        personagemRepository.deleteAll(personagens);
        animeRepository.deleteById(id);
    }

    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado." ));
    }
}
