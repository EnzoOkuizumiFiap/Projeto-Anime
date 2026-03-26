package br.com.fiap.anime.services;

import br.com.fiap.anime.models.Anime;
import br.com.fiap.anime.repositories.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;

    public List<Anime> getAllAnimes() {
        return animeRepository.findAll();
    }

    public Anime getAnimeById(Long id) {
        return findAnimeById(id);
    }

    public Anime addAnime(Anime anime) {
        if (anime.getPersonagens() != null) {
            anime.getPersonagens().forEach(personagem -> personagem.setAnime(anime));
        }
        return animeRepository.save(anime);
    }

    public Anime updateAnime(Long id, Anime newAnime) {
        if (newAnime.getPersonagens() != null) {
            newAnime.getPersonagens().forEach(personagem -> personagem.setAnime(newAnime));
        }
        newAnime.setId(findAnimeById(id).getId());
        return animeRepository.save(newAnime);
    }

    public void deleteAnime(Long id) {
        findAnimeById(id);
        animeRepository.deleteById(id);
    }

    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado." ));
    }
}
