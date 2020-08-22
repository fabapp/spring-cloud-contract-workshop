package de.fabiankrueger.scc.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPreparedEvent {
    private Long orderId;
}
