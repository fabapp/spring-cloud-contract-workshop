package de.fabiankrueger.scc.cashier;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CashierController {

    private final CashierService cashierService;

    @PostMapping("/order")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        final Order processedOrder = cashierService.processOrder(order);
        return ResponseEntity.ok(processedOrder);
    }

    @PostMapping("/order/{orderId}/payment")
    public ResponseEntity<Payment> processPayment(@RequestBody Payment payment, @PathVariable Long orderId) {
        final Payment processedPayment = cashierService.processPayment(orderId, payment);
        return ResponseEntity.ok(processedPayment);
    }
}
