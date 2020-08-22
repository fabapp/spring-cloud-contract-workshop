package de.fabiankrueger.scc.cashier;

import org.springframework.stereotype.Component;

@Component
public class PriceCalculator {
    public double calculatePrice(String product, int qty) {
        if("coffee".equals(product)) return qty * 1.43;
        throw new IllegalArgumentException("Unknown product '" + product + "'");
    }
}
