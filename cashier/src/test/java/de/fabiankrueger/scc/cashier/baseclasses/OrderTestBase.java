package de.fabiankrueger.scc.cashier.baseclasses;

import de.fabiankrueger.scc.cashier.CashierController;
import de.fabiankrueger.scc.cashier.CashierService;
import de.fabiankrueger.scc.cashier.Order;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CashierController.class)
@AutoConfigureMockMvc
public abstract class OrderTestBase {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CashierService cashierService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        when(cashierService.processOrder(any(Order.class))).thenAnswer(o -> {
            Order processedOrder = (Order) o.getArgument(0);
            processedOrder.setId(1L);
            processedOrder.setTimeOrdered(Instant.now());
            processedOrder.setAmount(2.86);
            return processedOrder;
        });
    }

}
