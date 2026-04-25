package enums;

public enum TipoDisciplina {
    MATEMATICA("Matemática"),
    PORTUGUES("Português"),
    INFORMATICA("Informática"),
    INGLES("Inglês"),
    FISICA("Fisíca"),
    DEFAULT("");

    private final String label;

    TipoDisciplina(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static TipoDisciplina[] getValues() {
        return VALUES;
    }

    private static final TipoDisciplina[] VALUES = values();
}
