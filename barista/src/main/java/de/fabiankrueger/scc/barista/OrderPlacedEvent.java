package de.fabiankrueger.scc.barista;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPlacedEvent {
    private Long orderId;
    private String product;
    private int qty;
}
