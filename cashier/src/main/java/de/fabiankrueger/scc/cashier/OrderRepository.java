package de.fabiankrueger.scc.cashier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //List<Order> findByStatus(String status);
}
