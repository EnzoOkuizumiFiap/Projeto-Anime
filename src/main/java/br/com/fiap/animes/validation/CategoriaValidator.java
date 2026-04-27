package br.com.fiap.animes.validation;

import br.com.fiap.animes.Anime.Categoria;
import br.com.fiap.animes.Anime.dto.AnimeRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public class CategoriaValidator implements ConstraintValidator<CategoriaValidation, AnimeRequest> {

    private final Set<Categoria> categoriasValidas = EnumSet.allOf(Categoria.class);

    @Override
    public boolean isValid(AnimeRequest animeRequest, ConstraintValidatorContext context) {
        if (!fieldIsValid(animeRequest.categoria()) || hasInvalidCategory(animeRequest.categoria())) {
            addViolation(context, "categoria", "Categoria inserida incorretamente!");
            return false;
        }
        return true;
    }

    private boolean hasInvalidCategory(Collection<Categoria> categorias) {
        return categorias.stream().anyMatch(c -> c == null || !categoriasValidas.contains(c));
    }

    private boolean fieldIsValid(Collection<Categoria> value) {
        return value != null && !value.isEmpty();
    }

    private void addViolation(ConstraintValidatorContext context, String field, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(field)
                .addConstraintViolation();
    }
}
