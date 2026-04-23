package br.com.fiap.animes.Anime;

import br.com.fiap.animes.Personagem.Personagem;
import br.com.fiap.animes.Personagem.PersonagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final PersonagemRepository personagemRepository;

    public List<Anime> getAllAnimes() {
        return animeRepository.findAll();
    }

    public Anime getAnimeById(Long id) {
        return findAnimeById(id);
    }

    public Anime create(Anime anime) {
        return animeRepository.save(anime);
    }

    public Anime update(Long id, Anime newAnime) {
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
