package br.com.fiap.animes.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = CategoriaValidator.class)
public @interface CategoriaValidation {
    String message() default "Categoria inválida.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
