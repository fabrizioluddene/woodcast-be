package it.woodcast.enumeration;

public enum RulesEnum {
    ADMINISTRATOR("ADMINISTRATOR"),
    PROGRAM_MANAGER("PROGRAM_MANAGER"),
    PRACTICE_LEADER("PRACTICE_LEADER");

    private final String value;

    RulesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
