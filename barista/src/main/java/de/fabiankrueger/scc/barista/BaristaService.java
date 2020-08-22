package de.fabiankrueger.scc.barista;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaristaService {

    private final OrderPreparedOutboundAdapter orderPreparedOutboundAdapter;

    public void onOrderPlaced(OrderPlacedEvent orderPlacedEvent) {
        prepareOrder(orderPlacedEvent);
    }

    private void prepareOrder(OrderPlacedEvent orderPlacedEvent) {
        try {
            Thread.sleep(200);
            OrderPreparedEvent orderPreparedEvent = new OrderPreparedEvent();
            orderPreparedEvent.setOrderId(orderPlacedEvent.getOrderId());
            publishOrderPreparedEvent(orderPreparedEvent);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void publishOrderPreparedEvent(OrderPreparedEvent orderPreparedEvent) {
        orderPreparedOutboundAdapter.publish(orderPreparedEvent);
    }
}
