package com.certis_base_app.ui.onboarding;

import java.util.HashMap;
import java.util.Map;

public enum ProgressLevel {
    STEP1(25),
    STEP2(50),
    STEP3(75),
    STEP4(100);

    private final int code;
    private static final Map<Integer, ProgressLevel> valuesByCode;

    static {
        valuesByCode = new HashMap<Integer, ProgressLevel>();
        for (ProgressLevel checkpoint : ProgressLevel.values()) {
            valuesByCode.put(checkpoint.code, checkpoint);
        }
    }

    ProgressLevel(Integer code) {
        this.code = code;
    }

    public static ProgressLevel lookupByCode(int code) {
        return valuesByCode.get(code);
    }

    public Integer getCode() {
        return code;
    }
}