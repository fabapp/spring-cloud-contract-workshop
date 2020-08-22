package de.fabiankrueger.scc.barista;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@EnableBinding(OrderPlacedInboundAdapter.OrderPlacedBinder.class)
@RequiredArgsConstructor
@Component
public class OrderPlacedInboundAdapter {

    private final BaristaService baristaService;

    public interface OrderPlacedBinder {
        String ORDER_PLACED = "orderPlaced";
        @Input(ORDER_PLACED)
        SubscribableChannel input();
    }

    @Autowired
    OrderPlacedBinder orderPlacedBinder;

    @StreamListener(OrderPlacedBinder.ORDER_PLACED)
    public void onOrderPlacedEvent(Message<OrderPlacedEvent> orderPlacedEventMessage) {
        baristaService.onOrderPlaced(orderPlacedEventMessage.getPayload());
    }
}
