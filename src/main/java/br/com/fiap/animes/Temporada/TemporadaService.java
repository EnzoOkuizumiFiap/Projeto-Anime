package br.com.fiap.animes.Temporada;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporadaService {

    private final TemporadaRepository temporadaRepository;

    public List<Temporada> findAll() {
        return temporadaRepository.findAll();
    }

    public Temporada findById(Long id) {
        return findTemporadaById(id);
    }

    public Temporada create(Temporada temporada) {
        return temporadaRepository.save(temporada);
    }

    public Temporada update(Long id, Temporada newTemporada) {
        newTemporada.setId(findTemporadaById(id).getId());
        return temporadaRepository.save(newTemporada);
    }

    public void delete(Long id) {
        findTemporadaById(id);
        temporadaRepository.deleteById(id);
    }

    private Temporada findTemporadaById(Long id) {
        return temporadaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado."));
    }
}
