package de.fabiankrueger.scc.cashier.baseclasses;

import de.fabiankrueger.scc.cashier.OrderPlacedEvent;
import de.fabiankrueger.scc.cashier.OrderPlacedEventOutboundAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
public abstract class OrderPlacedEventBase {

    @Autowired
    OrderPlacedEventOutboundAdapter orderPlacedEventOutboundAdapter;

    public void publishOrderPlacedEvent() {
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(1L, "coffee", 2);
        orderPlacedEventOutboundAdapter.publish(orderPlacedEvent);
    }
}
