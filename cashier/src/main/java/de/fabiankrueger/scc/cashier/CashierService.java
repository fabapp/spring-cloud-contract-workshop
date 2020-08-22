package de.fabiankrueger.scc.cashier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashierService {

    private final PriceCalculator priceCalculator;
    private final OrderRepository orderRepository;
    private final OrderPlacedEventOutboundAdapter orderPlacedEventOutboundAdapter;

    public Order processOrder(Order order) {
        double amount = priceCalculator.calculatePrice(order.getProduct(), order.getQty());
        order.setAmount(amount);
        orderRepository.save(order);
        return order;
    }

    public Payment processPayment(Long orderId, Payment customerPyment) {
        final Order order = orderRepository.getOne(orderId);
        Payment payment = new Payment(order.getAmount(), customerPyment.getAmountGiven().doubleValue());
        order.setPayment(payment);
        publishOrderPlacedEvent(order);
        return payment;
    }

    private void publishOrderPlacedEvent(Order order) {
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getId(), order.getProduct(), order.getQty());
        orderPlacedEventOutboundAdapter.publish(orderPlacedEvent);
    }
}