package com.certis_base_app.enums;

public enum Visibility {
        VISIBLE("true"),
        NOT_VISIBLE("false");
        private String value;
        
        Visibility(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
        public static Visibility fromString(String text) {
            for (Visibility b : Visibility.values()) {
                if (b.value.equalsIgnoreCase(text)) {
                    return b;
                }
            }
            return null;
        }
    }
    