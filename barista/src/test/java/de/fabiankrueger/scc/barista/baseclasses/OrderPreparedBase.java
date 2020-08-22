package de.fabiankrueger.scc.barista.baseclasses;

import de.fabiankrueger.scc.barista.OrderPreparedEvent;
import de.fabiankrueger.scc.barista.OrderPreparedOutboundAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;

@SpringBootTest
@AutoConfigureMessageVerifier
public abstract class OrderPreparedBase {

    @Autowired
    OrderPreparedOutboundAdapter orderPreparedOutboundAdapter;

    public void publishOrderPreparedEvent() {
        OrderPreparedEvent orderPreparedEvent = new OrderPreparedEvent();
        orderPreparedEvent.setOrderId(1L);
        orderPreparedOutboundAdapter.publish(orderPreparedEvent);
    }
}
