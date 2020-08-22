package de.fabiankrueger.scc.cashier;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class OrderPlacedEvent {
    private final Long orderId;
    private final String product;
    private final int qty;
}
