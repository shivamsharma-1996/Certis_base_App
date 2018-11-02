package com.certis_base_app.ui.onboarding.registration;

import java.util.HashMap;
import java.util.Map;

public enum EnumProgressLevel {
    STEP1(25),
    STEP2(50),
    STEP3(75),
    STEP4(100);

    private final int code;
    private static final Map<Integer, EnumProgressLevel> valuesByCode;

    static {
        valuesByCode = new HashMap<Integer, EnumProgressLevel>();
        for (EnumProgressLevel checkpoint : EnumProgressLevel.values()) {
            valuesByCode.put(checkpoint.code, checkpoint);
        }
    }

    EnumProgressLevel(Integer code) {
        this.code = code;
    }

    public static EnumProgressLevel lookupByCode(int code) {
        return valuesByCode.get(code);
    }

    public Integer getCode() {
        return code;
    }
}