package models.enums;

public enum Gender {
    MALE("Male"), FEMALE("Female"), NONE("None");

    public final String label;

    Gender(String label) {
        this.label = label;
    }
}
