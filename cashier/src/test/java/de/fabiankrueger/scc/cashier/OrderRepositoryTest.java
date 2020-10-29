package de.fabiankrueger.scc.cashier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = true)
@Rollback(false)
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void persistOrder() throws InterruptedException {
        Order order = new Order();
        order.setAmount(12.30);
        order.setProduct("Banana");

        Order savedOrder1 = orderRepository.save(order);
        testEntityManager.getEntityManager().flush();
        assertThat(savedOrder1.getTimeOrdered()).isNotNull();

        order.setTimeOrdered(Instant.now());
        Order savedOrder2 = orderRepository.save(order);
        testEntityManager.getEntityManager().flush();
        assertThat(savedOrder1.getTimeOrdered()).isEqualTo(savedOrder2.getTimeOrdered());
    }
}
