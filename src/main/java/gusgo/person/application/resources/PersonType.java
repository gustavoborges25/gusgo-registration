package gusgo.person.application.resources;

public enum PersonType {

    NATURAL("Física", "NATURAL"),
    JURIDICAL("Jurídica", "JURIDICAL");

    private final String name;

    private final String value;

    PersonType(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
