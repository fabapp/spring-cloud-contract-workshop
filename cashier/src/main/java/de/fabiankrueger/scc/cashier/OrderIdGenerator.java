package de.fabiankrueger.scc.cashier;

import org.springframework.stereotype.Component;

@Component
public class OrderIdGenerator {
    private int nextId = 1;

    public int getNextOrderId() {
        return nextId++;
    }
}
