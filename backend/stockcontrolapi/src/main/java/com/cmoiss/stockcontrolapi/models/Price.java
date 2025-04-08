package com.cmoiss.stockcontrolapi.models;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class Price {
    private BigDecimal price;

    public Price(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }

        // 1º Compara valor com Zero
        // compareTo retorna [-1 (menor), 0 (igual), 1 (maior)]

        // 2º Se for negativo, o valor é Zero
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.price = price;
    }

    public Price(double price) {
        this(BigDecimal.valueOf(price));
    }

    public Price(Double price) {
        this(BigDecimal.valueOf(price));
    }
}
