package de.fabiankrueger.scc.cashier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPreparedEvent {
    private Long orderId;
}
