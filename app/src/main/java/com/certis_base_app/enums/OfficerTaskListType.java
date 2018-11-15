package com.certis_base_app.enums;

import java.util.HashMap;
import java.util.Map;

public enum OfficerTaskListType
{
    TASKS_HEADER(1), TASKS_IN_PROGRESS_HEADER(2), TASKS_DETAIL(3);

    private final int code;
    private static final Map<Integer, OfficerTaskListType> valuesByCode;

    static {
        valuesByCode = new HashMap<>();
        for (OfficerTaskListType checkpoint : OfficerTaskListType.values()) {
            valuesByCode.put(checkpoint.code, checkpoint);
        }
    }

    OfficerTaskListType(Integer code) {
        this.code = code;
    }

    public static OfficerTaskListType lookupByCode(int code)
    {
        return valuesByCode.get(code);
    }

    public Integer getCode() {
        return code;
    }
}