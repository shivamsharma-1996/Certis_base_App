package com.certis_base_app.ui.onboarding.registration;

public class CountryCode {
    String name;
    int flag;

    public CountryCode() {
    }

    public CountryCode(String name, int flag) {
        this.name = name;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "CountryCode{" +
                "name='" + name + '\'' +
                ", flag=" + flag +
                '}';
    }
}
