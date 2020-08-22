package de.fabiankrueger.scc.barista;

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

@SpringBootTest
@AutoConfigureStubRunner(
        ids = "de.fabiankrueger.scc:cashier:+:stubs",
        stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class OrdersPlacedTest {

    @Autowired
    StubFinder stubFinder;

    @MockBean
    BaristaService baristaService;

    @Test
    void baristaReceivesOrderPlacedEvents() {

        // trigger sending of OrderPlacedEvent from Cashier (producer)
        stubFinder.trigger("orderPlacedEvent");

        // capture event passed from OrderPlacedInboundAdapter to BaristaService
        ArgumentCaptor<OrderPlacedEvent> orderPlacedEventArgumentCaptor = ArgumentCaptor.forClass(OrderPlacedEvent.class);
        verify(baristaService).onOrderPlaced(orderPlacedEventArgumentCaptor.capture());

        // verify event has required data
        final OrderPlacedEvent orderPlacedEvent = orderPlacedEventArgumentCaptor.getValue();
        assertThat(orderPlacedEvent.getProduct()).isEqualTo("coffee");
        assertThat(orderPlacedEvent.getQty()).isEqualTo(2);
    }

}
