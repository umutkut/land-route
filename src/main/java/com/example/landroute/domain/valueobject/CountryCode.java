package com.example.landroute.domain.valueobject;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.ToString;

@ToString
public class CountryCode {
    @Getter
    private final String value;

    public CountryCode(String value) {
        Preconditions.checkArgument(value.length() == 3);
        this.value = value;
    }
}
