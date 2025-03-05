package gusgo.tdc.application.resources;

public class ValidationConstants {

    private ValidationConstants() {}

    public static final String DOCUMENT_CPF_IS_REQUIRED = isRequired("Document CPF");

    public static final String DOCUMENT_CNPJ_IS_REQUIRED = isRequired("Document CNPJ");

    public static final String INVALID_PERSON_TYPE = "Invalid person type";

    public static final String INVALID_FILE = "Invalid file";

    public static String isRequired (String field) {
        return String.format("%s is required.", field);
    }
}
