package br.com.fiap.animes.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class SemCaractereEspecialValidator implements ConstraintValidator<SemCaractereEspecial, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true;

        boolean ok = value.matches("^[a-zA-ZÀ-ÿ\\s]+$");

        if (!ok) addViolation(context, "O campo não pode conter caracteres especiais");

        return ok;
    }

    private void addViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}