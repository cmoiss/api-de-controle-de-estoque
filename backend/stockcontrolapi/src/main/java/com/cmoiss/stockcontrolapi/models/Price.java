package com.cmoiss.stockcontrolapi.models;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class Price {
    private BigDecimal value;

    public Price(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        // 1º Compara valor com Zero
        // compareTo retorna [-1 (menor), 0 (igual), 1 (maior)]

        // 2º Se for negativo, o valor é Zero
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.value = value;
    }
}
