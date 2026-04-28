package br.com.fiap.animes.validation;

import br.com.fiap.animes.Personagem.Personagem;
import br.com.fiap.animes.Personagem.PersonagemRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class NomePersonagemValidator implements ConstraintValidator<NomePersonagem, String> {

    private final PersonagemRepository personagemRepository;

    @Override
    public boolean isValid(String nome, ConstraintValidatorContext context) {
        Optional<Personagem> personagemComNome = personagemRepository.findByNomeIgnoreCase(nome);

        if (personagemComNome.isEmpty()) return true;

        Long personagemIdPath = ValidationUtils.extractIdFromPath();
        boolean valid = personagemIdPath != null && personagemComNome.get().getId().equals(personagemIdPath);

        if (!valid) addViolation(context, "Esse Nome de personagem já foi utilizado, insira outro!");

        return valid;
    }

    private void addViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
    }
}