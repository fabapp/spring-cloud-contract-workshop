package de.fabiankrueger.scc.cashier;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class Payment {
    private BigDecimal amountAsked;
    private BigDecimal amountGiven;
    private BigDecimal changeReturned;

    public Payment(double amount, double paid) {
        this.amountAsked = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
        this.amountGiven = new BigDecimal(paid).setScale(2, RoundingMode.HALF_UP);
        this.changeReturned = this.amountGiven.subtract(this.amountAsked);
    }
}
