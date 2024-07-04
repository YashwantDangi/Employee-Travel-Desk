package com.etd.enumeration;

public enum UserRoleEnum {
    Employee("Employee"),
    TravelDeskExec("TravelDeskExec"),
    HR("HR");

    String value;
    UserRoleEnum(String value) {
        this.value = value;
    }
}
