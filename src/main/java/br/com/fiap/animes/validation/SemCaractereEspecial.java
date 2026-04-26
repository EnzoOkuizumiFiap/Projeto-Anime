package br.com.fiap.animes.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = SemCaractereEspecialValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface SemCaractereEspecial {
    String message() default "O campo não pode conter caracteres especiais";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
