package br.com.fiap.animes.validation;

import br.com.fiap.animes.Anime.Anime;
import br.com.fiap.animes.Anime.AnimeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TituloValidator implements ConstraintValidator<Titulo, String> {

    private final AnimeRepository animeRepository;

    @Override
    public boolean isValid(String titulo, ConstraintValidatorContext context) {
        Optional<Anime> animeComTitulo = animeRepository.findByTituloIgnoreCase(titulo);
        if (animeComTitulo.isEmpty()) return true;

        Long animeIdPath = extractAnimeIdFromPath();
        boolean valid = animeIdPath != null && animeComTitulo.get().getId().equals(animeIdPath);

        if (!valid) addViolation(context, "Esse Título de anime já foi utilizado, insira outro!");

        return valid;
    }

    private void addViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }

    private Long extractAnimeIdFromPath() {
        if (!(RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes attributes)) {
            return null;
        }

        Object uriVariables = attributes.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (!(uriVariables instanceof Map<?, ?> variables) || !variables.containsKey("id")) {
            return null;
        }

        try {
            return Long.parseLong(variables.get("id").toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
