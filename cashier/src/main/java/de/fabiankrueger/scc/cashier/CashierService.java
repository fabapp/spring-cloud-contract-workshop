package de.fabiankrueger.scc.cashier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Payment processPayment(Long orderId, Double amountGiven) {
        final Order order = orderRepository.getOne(orderId);
        Payment payment = new Payment(order.getAmount(), amountGiven);
        order.setPayment(payment);
        publishOrderPlacedEvent(order);
        return payment;
    }

    private void publishOrderPlacedEvent(Order order) {
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getId(), order.getProduct(), order.getQty());
        orderPlacedEventOutboundAdapter.publish(orderPlacedEvent);
    }
}