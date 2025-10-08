package bci.works;

/**
 * Enum representing the category of a work in the library.
 * The toString() is overridden to match the expected display strings
 * used by the automatic tests (Portuguese labels).
 */
public enum Category {
    REFERENCE("Referência"),
    FICTION("Ficção"),
    SCITECH("Técnica e Científica");

    private final String display;

    Category(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return display;
    }
}
