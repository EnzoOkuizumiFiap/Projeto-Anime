package br.com.fiap.animes.validation;

import br.com.fiap.animes.Anime.AnimeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TituloValidator implements ConstraintValidator<Titulo, String> {

    private final AnimeRepository animeRepository;

    @Override
    public boolean isValid(String titulo, ConstraintValidatorContext context) {
        if (titulo == null || titulo.isBlank()) {
            return true;
        }

        boolean valid = !animeRepository.existsByTituloIgnoreCase(titulo);
        if (!valid) {
            addViolation(context, "Esse Título de anime já foi utilizado, insira outro!");
        }

        return valid;
    }

    private void addViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }

}
