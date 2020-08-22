package de.fabiankrueger.scc.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@EnableBinding(OrderPreparedInboundAdapter.OrderPreparedBinder.class)
@RequiredArgsConstructor
@Component
public class OrderPreparedInboundAdapter {

    public static final String BARISTA_HEADER = "barista";
    private final CustomerService customerService;

    public interface OrderPreparedBinder {
        String ORDER_PREPARED = "orderPrepared";
        @Input(ORDER_PREPARED)
        SubscribableChannel input();
    }

    @StreamListener(OrderPreparedBinder.ORDER_PREPARED)
    public void onOrderPreparedEvent(Message<OrderPreparedEvent> orderPreparedEventMessage) {
        customerService.onOrderPrepared(orderPreparedEventMessage.getPayload(), orderPreparedEventMessage.getHeaders().get(BARISTA_HEADER).toString());
    }
}
