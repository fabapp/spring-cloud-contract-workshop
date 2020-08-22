package de.fabiankrueger.scc.cashier;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ordering")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private String product;
    private int qty;
    private double amount;
    @Embedded
    private Payment payment;
}
