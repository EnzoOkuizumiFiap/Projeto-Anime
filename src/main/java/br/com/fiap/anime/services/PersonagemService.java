package br.com.fiap.anime.services;

import br.com.fiap.anime.models.Personagem;
import br.com.fiap.anime.repositories.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    public List<Personagem> getAllPersonagens() {
        return personagemRepository.findAll();
    }

    public Personagem addPersonagem(Personagem personagem ) {
        return personagemRepository.save(personagem);
    }

    public Personagem getPersonagemById(Long id) {
        return findPersonagemById(id);
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
