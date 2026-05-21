package br.com.fiap.animes.Personagem;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.AnimeRepository;
import br.com.fiap.animes.Personagem.dto.PersonagemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("personagens")
    public Page<Personagem> findAll(Pageable pageable) {
        return personagemRepository.findAll(pageable);
    }

    @Cacheable(value = "personagens", key = "#id")
    public Personagem findById(Long id) {
        return findPersonagemById(id);
    }

    @Cacheable(value = "personagens", key = "'by-anime/' + #animeId")
    public Page<Personagem> findAllByAnimeId(Long animeId, Pageable pageable) {
        findAnimeById(animeId);
        return personagemRepository.findByAnimeId(animeId, pageable);
    }

    @Cacheable(value = "personagens", key = "'by-name/' + #nome")
    public List<PersonagemSummary> findByNome(String nome) {
        return personagemRepository.findByNomeContainingIgnoreCase(nome);
    }

    @CacheEvict(value = "personagens", allEntries = true)
    public Personagem create(PersonagemRequest request) {
        Anime animeFound = findAnimeById(request.animeId());
        return personagemRepository.save(request.toEntity(animeFound));
    }

    @CacheEvict(value = "personagens", allEntries = true)
    public Personagem update(Long id, PersonagemRequest request) {
        findPersonagemById(id);

        Anime foundAnime = findAnimeById(request.animeId());
        Personagem personagem = request.toEntity(foundAnime);
        personagem.setId(id);

        return personagemRepository.save(personagem);
    }

    @CacheEvict(value = "personagens", allEntries = true)
    public void delete(Long id) {
        findPersonagemById(id);
        personagemRepository.deleteById(id);
    }

    @Cacheable
    private Personagem findPersonagemById(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem com id " + id + " não encontrado"));
    }

    @Cacheable
    private Anime findAnimeById(Long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado"));
    }
}