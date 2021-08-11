package br.com.rstore.rent.Config.Validation;

public class FormErrorDTO {

    private String invalidField;
    private String errorMessage;

    public FormErrorDTO(String invalidField, String errorMessage) {
        this.invalidField = invalidField;
        this.errorMessage = errorMessage;
    }

    public String getInvalidField() {
        return invalidField;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
