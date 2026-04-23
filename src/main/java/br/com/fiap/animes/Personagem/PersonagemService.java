package br.com.fiap.animes.Personagem;

import br.com.fiap.animes.Anime.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private AnimeRepository animeRepository;

    public List<Personagem> getAllPersonagens() {
        return personagemRepository.findAll();
    }

    public Personagem addPersonagem(Personagem personagem) {
        return personagemRepository.save(personagem);
    }

    public Personagem getPersonagemById(Long id) {
        return findPersonagemById(id);
    }

    public List<Personagem> getAllPersonagensByAnimeId(Long id) {
        if (!animeRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado");

        List<Personagem> personagens = personagemRepository.findByAnimeId(id);
        if (personagens.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum personagem encontrado para este Anime");
        return personagens;
    }

    public void deletePersonagem(Long id) {
        findPersonagemById(id);
        personagemRepository.deleteById(id);
    }

    public Personagem updatePersonagem(Long id, Personagem newPersonagem) {
        findPersonagemById(id);
        newPersonagem.setId(id);
        return personagemRepository.save(newPersonagem);
    }

    private Personagem findPersonagemById(Long id) {
        return personagemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem com id " + id + " não encontrado"));
    }
}
