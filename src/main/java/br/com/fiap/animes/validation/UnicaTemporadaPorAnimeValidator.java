package br.com.fiap.animes.validation;

import br.com.fiap.animes.Temporada.TemporadaRepository;
import br.com.fiap.animes.Temporada.dto.TemporadaRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import br.com.fiap.animes.Temporada.Temporada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnicaTemporadaPorAnimeValidator implements ConstraintValidator<UnicaTemporadaPorAnime, TemporadaRequest> {

    @Autowired
    private TemporadaRepository temporadaRepository;

    @Override
    public boolean isValid(TemporadaRequest value, ConstraintValidatorContext context) {
        if (value == null) return true;
        Long idPath = ValidationUtils.extractIdFromPath();
        java.util.Optional<Temporada> existente = temporadaRepository.findByAnimeIdAndNumTemporada(value.animeId(), value.numTemporada());
        boolean valid = existente.isEmpty() || (idPath != null && existente.get().getId().equals(idPath));
        if (!valid) {
            addViolation(context, "Já existe uma temporada com esse número para este anime.");
        }
        return valid;
    }

    private void addViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode("numTemporada")
                .addConstraintViolation();
    }
}
