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
 public AnimeRepository repository;

 public List<Anime> getAllAnimes() {
  return repository.findAll();
 }

  public Anime getAnimeById(Long id) {
   return findAnimeById(id);
  }

 public Anime addAnime(Anime anime) {
  return repository.save(anime);
 }

 public Anime updateAnime(Long id, Anime newAnime) {
  newAnime.setId(findAnimeById(id).getId());
  return repository.save(newAnime);
 }

 public void deleteAnime(Long id) {
  findAnimeById(id);
  repository.deleteById(id);
 }

 private Anime findAnimeById(Long id) {
  return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime com id " + id + " não encontrado." ));
 }
}
