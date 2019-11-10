package com.anubhav.calculas.tokenization;

import java.util.Objects;

public class Number implements Token {
    private Double value;

    public Number(Double value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value.toString();
    }

    public Double getNumericValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return Objects.equals(value, number.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
