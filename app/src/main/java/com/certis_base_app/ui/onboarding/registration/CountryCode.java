package com.certis_base_app.ui.onboarding.registration;

public class CountryCode {
    String codes;
    int flag;

    public CountryCode(String codes, int flag) {
        this.codes = codes;
        this.flag = flag;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
