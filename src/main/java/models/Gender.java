package models;

public enum Gender {
    MALE("Male"), FEMALE("Female"), NONE("None");

    public final String label;

    private Gender(String _label) {
        this.label = _label;
    }
}
