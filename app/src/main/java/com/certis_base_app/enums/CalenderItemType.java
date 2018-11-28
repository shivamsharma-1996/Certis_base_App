package com.certis_base_app.enums;

import java.util.HashMap;
import java.util.Map;

public enum CalenderItemType
{
    FIRST_ROW(1), OTHER_ROW(2);
    private final int code;
    private static final Map<Integer, CalenderItemType> valuesByCode;

    static
    {
        valuesByCode = new HashMap<>();
        for (CalenderItemType checkpoint : CalenderItemType.values()) {
            valuesByCode.put(checkpoint.code, checkpoint);
        }
    }

    CalenderItemType(Integer code) {
        this.code = code;
    }

    public static CalenderItemType lookupByCode(int code)
    {
        return valuesByCode.get(code);
    }

    public Integer getCode() {
        return code;
    }
}