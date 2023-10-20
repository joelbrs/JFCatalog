package br.com.joelbrs.JFCatalog.controllers.exceptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public void addError(FieldMessage fieldMessage) {
        errors.add(fieldMessage);
    }
}
