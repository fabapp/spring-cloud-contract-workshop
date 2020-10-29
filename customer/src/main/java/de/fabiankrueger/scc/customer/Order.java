package de.fabiankrueger.scc.customer;

import lombok.Data;

@Data
public class Order {
    private String product;
    private String mobileNumber;
    private int qty;
}
