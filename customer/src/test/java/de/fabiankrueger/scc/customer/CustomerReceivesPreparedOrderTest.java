package de.fabiankrueger.scc.customer;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "de.fabiankrueger.scc:barista:+:stubs", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class CustomerReceivesPreparedOrderTest {

    @Autowired
    StubFinder stubFinder;

    @MockBean
    CustomerService customerService;

    @Test
    public void customerReceivesPreparedOrderEvent() {

        stubFinder.trigger("orderPreparedEvent");

        ArgumentCaptor<OrderPreparedEvent> orderPreparedEventArgumentCaptor = ArgumentCaptor.forClass(OrderPreparedEvent.class);
        ArgumentCaptor<String> baristaArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(customerService).onOrderPrepared(orderPreparedEventArgumentCaptor.capture(), baristaArgumentCaptor.capture());

        String barista = baristaArgumentCaptor.getValue();
        OrderPreparedEvent orderPreparedEvent = orderPreparedEventArgumentCaptor.getValue();
        assertThat(barista).isEqualTo("Jane Doe");
        assertThat(orderPreparedEvent.getOrderId()).isEqualTo(1L);

    }

}
