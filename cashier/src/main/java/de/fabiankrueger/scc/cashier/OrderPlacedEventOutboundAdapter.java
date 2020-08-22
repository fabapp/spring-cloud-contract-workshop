package de.fabiankrueger.scc.cashier;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@EnableBinding(OrderPlacedEventOutboundAdapter.OrderPlacedEventBinder.class)
@AllArgsConstructor
@Component
public class OrderPlacedEventOutboundAdapter {

    public interface OrderPlacedEventBinder {
        String TOPIC = "orderPlaced";
        @Output(TOPIC)
        MessageChannel channel();
    }

    private final OrderPlacedEventBinder orderPlacedEventBinder;

    public void publish(OrderPlacedEvent orderPlacedEvent) {
        final Message<OrderPlacedEvent> message = MessageBuilder.withPayload(orderPlacedEvent).setHeader("contentType", "application/json").build();
        orderPlacedEventBinder.channel().send(message);
    }
}
