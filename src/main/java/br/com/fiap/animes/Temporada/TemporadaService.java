package br.com.fiap.animes.Temporada;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemporadaService {

    private final TemporadaRepository temporadaRepository;



    public List<Temporada> findAll() {
        return temporadaRepository.findAll();
    }

    public Temporada findTemporadaById(Long id) {
        return temporadaRepository.findById(id).get();
    }

    public Temporada create(Temporada temporada) {
        return temporadaRepository.save(temporada);
    }



}
