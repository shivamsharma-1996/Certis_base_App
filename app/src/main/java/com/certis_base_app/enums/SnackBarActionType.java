package com.certis_base_app.enums;

public enum SnackBarActionType {
    DISMISSS("DISMISS"),
    VIEW("VIEW");

    private String value;

    SnackBarActionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SnackBarActionType fromString(String text) {
        for (SnackBarActionType b : SnackBarActionType.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}