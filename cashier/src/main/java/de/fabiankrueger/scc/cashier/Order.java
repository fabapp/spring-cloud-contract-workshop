package de.fabiankrueger.scc.cashier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ordering")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreationTimestamp
    private Instant timeOrdered;
    private String mobileNumber;
    private String product;
    private int qty;
    private double amount;
    @Embedded
    private Payment payment;
    /*
    private Instant timePrepared;
    private String barista;
    private String status;

    @PrePersist
    public void calculateStatus() {
        if(timePrepared == null && payment == null) {
            status = "ORDERED";
        }
        else if(timePrepared == null) {
            status =  "PAID";
        }
        status = "PREPARED";
    }
    */
}
