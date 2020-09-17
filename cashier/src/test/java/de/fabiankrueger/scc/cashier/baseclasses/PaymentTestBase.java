package de.fabiankrueger.scc.cashier.baseclasses;

import de.fabiankrueger.scc.cashier.CashierController;
import de.fabiankrueger.scc.cashier.CashierService;
import de.fabiankrueger.scc.cashier.Order;
import de.fabiankrueger.scc.cashier.Payment;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

@WebMvcTest(CashierController.class)
@AutoConfigureMockMvc
public abstract class PaymentTestBase {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CashierService cashierService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        Order paidOrder = new Order();
        paidOrder.setId(1L);
        paidOrder.setProduct("coffee");
        paidOrder.setQty(2);
        final double amountAsked = 2.86;
        final double amountGiven = 3.0;
        paidOrder.setAmount(amountAsked);
        Payment payment = new Payment(amountAsked, amountGiven);
        paidOrder.setPayment(payment);
        Payment processedPayment = new Payment(amountAsked, amountGiven);
        when(cashierService.processPayment(paidOrder.getId(), amountGiven)).thenReturn(processedPayment);
    }

}
