package de.fabiankrueger.scc.barista;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBinding(OrderPreparedOutboundAdapter.OrderPreparedBinder.class)
public class OrderPreparedOutboundAdapter {

    public interface OrderPreparedBinder {
        String ORDER_PREPARED = "orderPrepared";
        @Output(ORDER_PREPARED)
        MessageChannel channel();
    }

    @Autowired
    private final OrderPreparedBinder orderPreparedBinder;

    public void publish(OrderPreparedEvent orderPreparedEvent) {
        final Message<OrderPreparedEvent> orderPreparedEventMessage = MessageBuilder
                .withPayload(orderPreparedEvent)
                .setHeader("barista", "Jane Doe")
                .build();
        orderPreparedBinder.channel().send(orderPreparedEventMessage);
    }
}
