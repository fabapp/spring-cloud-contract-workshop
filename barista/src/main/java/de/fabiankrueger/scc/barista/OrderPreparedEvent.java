package de.fabiankrueger.scc.barista;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPreparedEvent {
    private Long orderId;
}
