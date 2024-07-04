package com.etd.enumeration;

public enum ReimbursementRequestsStatusEnum {
    NEW("NEW"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    String value;

    ReimbursementRequestsStatusEnum(String value) {
        this.value = value;
    }
}