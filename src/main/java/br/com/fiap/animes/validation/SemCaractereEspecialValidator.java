package br.com.fiap.animes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SemCaractereEspecialValidator implements ConstraintValidator<SemCaractereEspecial, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true;
        return value.matches("^[\\p{L}\\s]+$");
    }
}
