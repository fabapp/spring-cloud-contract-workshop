package de.fabiankrueger.scc.cashier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class Payment {
    private BigDecimal amountAsked;
    private BigDecimal amountGiven;

    @JsonCreator
    public Payment(@JsonProperty("amountAsked") double amountAsked, @JsonProperty("amountGiven") double amountGiven) {
        this.amountAsked = new BigDecimal(amountAsked).setScale(2, RoundingMode.HALF_UP);
        this.amountGiven = new BigDecimal(amountGiven).setScale(2, RoundingMode.HALF_UP);
    }

    @Transient
    public BigDecimal getChangeReturned() {
        return this.amountGiven.subtract(this.amountAsked);
    }
}
