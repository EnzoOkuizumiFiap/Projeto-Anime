package br.com.fiap.animes.Personagem;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.AnimeRepository;
import br.com.fiap.animes.Personagem.dto.PersonagemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonagemService {
    private final PersonagemRepository personagemRepository;

    private final AnimeRepository animeRepository;

    public List<Personagem> findAll() {
        return personagemRepository.findAll();
    }

    public Personagem findById(Long id) {
        return findPersonagemById(id);
    }

    public Page<Personagem> findAllByAnimeId(Long id, Pageable pageable) {
        if (!animeRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado");

        Page<Personagem> personagens = personagemRepository.findByAnimeId(id, pageable);
        if (personagens.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum personagem encontrado para este Anime");

        return personagens;
    }

    public Personagem create(PersonagemRequest request) {
        Anime anime = findAnimeById(request.animeId());
        return personagemRepository.save(request.toEntity(anime));
    }

    public Personagem update(Long id, PersonagemRequest request) {
        Personagem personagem = findPersonagemById(id);

        Anime anime = findAnimeById(request.animeId());
        personagem.setAnime(anime);

        return personagemRepository.save(personagem);
    }

    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id).get();
    }

    public void delete(Long id) {
        findPersonagemById(id);
        personagemRepository.deleteById(id);
    }


    private Personagem findPersonagemById(Long id) {
        return personagemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem com id " + id + " não encontrado"));
    }

    public List<Personagem> findByNome(String nome) {
        return personagemRepository.findByNome(nome);
    }
}
