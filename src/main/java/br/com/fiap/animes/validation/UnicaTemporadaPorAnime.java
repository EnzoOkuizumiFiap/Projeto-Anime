package br.com.fiap.animes.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UnicaTemporadaPorAnimeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnicaTemporadaPorAnime {
    String message() default "Erro em temporada com duplicidade.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
