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
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Constraint(validatedBy = CategoriaValidator.class)
public @interface CategoriaValidation {
    Class<? extends Enum<?>> enumClass();

    String message() default "Categoria inserida incorretamente!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
