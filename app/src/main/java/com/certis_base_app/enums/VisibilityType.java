package com.certis_base_app.enums;

public enum VisibilityType {
        VISIBLE("true"),
        NOT_VISIBLE("false");
        private String value;
        
        VisibilityType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
        public static VisibilityType fromString(String text) {
            for (VisibilityType b : VisibilityType.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }

    public void setValue(String value) {
        this.value = value;
    }
}
    