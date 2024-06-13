package spring.boot.java_rest_api.validation;

import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class Validation {
    public static Map<String, String> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "Field " + error.getField() + ": " + error.getDefaultMessage());
        });

        return errors;
    }
}
