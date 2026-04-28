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

@Service
@RequiredArgsConstructor
public class PersonagemService {
    private final PersonagemRepository personagemRepository;
    private final AnimeRepository animeRepository;

    public Page<Personagem> findAll(Pageable pageable) {
        return personagemRepository.findAll(pageable);
    }

    public Personagem findById(Long id) {
        return findPersonagemById(id);
    }

    public Page<Personagem> findAllByAnimeId(Long animeId, Pageable pageable) {
        findAnimeById(animeId);
        return personagemRepository.findByAnimeId(animeId, pageable);
    }

    public Page<PersonagemSummary> findByNome(String nome, Pageable pageable) {
        return personagemRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Personagem create(PersonagemRequest request) {
        Anime animeFound = findAnimeById(request.animeId());
        return personagemRepository.save(request.toEntity(animeFound));
    }

    public Personagem update(Long id, PersonagemRequest request) {
        findPersonagemById(id);

        Anime foundAnime = findAnimeById(request.animeId());
        Personagem personagem = request.toEntity(foundAnime);
        personagem.setId(id);

        return personagemRepository.save(personagem);
    }

    public void delete(Long id) {
        findPersonagemById(id);
        personagemRepository.deleteById(id);
    }


    private Personagem findPersonagemById(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem com id " + id + " não encontrado"));
    }

    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado"));
    }
}
