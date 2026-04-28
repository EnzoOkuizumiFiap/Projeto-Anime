package br.com.fiap.animes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CategoriaValidator implements ConstraintValidator<CategoriaValidation, Collection<?>> {
    private Set<String> acceptedValues;

    @Override
    public void initialize(CategoriaValidation annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(Collection<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value.stream()
                .allMatch(item -> item != null && acceptedValues.contains(item.toString().toUpperCase()));
    }
}
