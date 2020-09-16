package de.fabiankrueger.scc.customer;

import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureMessageVerifier
public class SccMessagingBaseClass {

    @MockBean
    CustomerService customerService;

    public void customerServiceHandlesMessage() {
        ArgumentCaptor<OrderPreparedEvent> orderPreparedEventArgumentCaptor = ArgumentCaptor.forClass(OrderPreparedEvent.class);
        ArgumentCaptor<String> baristaArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(customerService).onOrderPrepared(orderPreparedEventArgumentCaptor.capture(), baristaArgumentCaptor.capture());

        String barista = baristaArgumentCaptor.getValue();
        OrderPreparedEvent orderPreparedEvent = orderPreparedEventArgumentCaptor.getValue();
        assertThat(barista).isEqualTo("Jane Doe");
        assertThat(orderPreparedEvent.getOrderId()).isEqualTo(10L);
    }
}
