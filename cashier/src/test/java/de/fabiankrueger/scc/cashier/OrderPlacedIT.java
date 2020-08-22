package de.fabiankrueger.scc.cashier;

import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.server.KafkaServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.support.GenericMessage;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=org.springframework.cloud.stream.test.binder.TestSupportBinderAutoConfiguration", // use "real" Kafka
        "spring.kafka.consumer.auto-offset-reset=earliest", // let the consumer read messages that might have been sent before consumer started
        "spring.cloud.stream.bindings.orderPlaced.destination=" + OrderPlacedIT.TOPIC,
        "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"})
@EmbeddedKafka
public class OrderPlacedIT {
    static final String TOPIC = "orders-placed";

    OrderPlacedEvent receivedOrderPlacedEvent;

    @Autowired
    EmbeddedKafkaBroker embeddedKafkaBroker;
    static EmbeddedKafkaBroker staticKafkaBroker;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    OrderPlacedEventOutboundAdapter  orderPlacedEventOutboundAdapter;

    @BeforeEach
    public void setup() {
        staticKafkaBroker = embeddedKafkaBroker;
    }

    @Test
    public void publishOrderPlacedEvent() {

        // create order placed event
        OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(1L, "coffee", 2);

        // send event to kafka
        orderPlacedEventOutboundAdapter.publish(orderPlacedEvent);

        // wait until event was received in {@link #onOrderPlacedEvent(GenericMessage orderPlacedEvent)}
        await().until(() -> receivedOrderPlacedEvent !=null);

        // verify that required event data was received
        assertThat(receivedOrderPlacedEvent.getProduct()).isEqualTo(orderPlacedEvent.getProduct());
        assertThat(receivedOrderPlacedEvent.getQty()).isEqualTo(orderPlacedEvent.getQty());

    }

    @KafkaListener(topics = {TOPIC}, groupId = "test-consumer")
    public void onOrderPlacedEvent(GenericMessage<?> orderPlacedEvent) throws IOException {
        receivedOrderPlacedEvent = objectMapper.readValue((byte[]) orderPlacedEvent.getPayload(), OrderPlacedEvent.class);
    }

    @AfterAll
    public static void tearDown() {
        staticKafkaBroker.getKafkaServers().forEach(KafkaServer::shutdown);
        staticKafkaBroker.getKafkaServers().forEach(KafkaServer::awaitShutdown);
    }
}
