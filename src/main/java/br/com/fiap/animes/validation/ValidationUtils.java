package br.com.fiap.animes.validation;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

public class ValidationUtils {

    private ValidationUtils() {}

    public static Long extractIdFromPath() {
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