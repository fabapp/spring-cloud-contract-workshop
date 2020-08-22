package de.fabiankrueger.scc.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public void onOrderPrepared(OrderPreparedEvent message, String barista) {
        System.out.println(barista + " prepared your order " + message.getOrderId());
    }
}
