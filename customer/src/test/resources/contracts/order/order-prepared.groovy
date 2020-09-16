package contracts.order;

org.springframework.cloud.contract.spec.Contract.make {
    name("customer-handles-order-prepared-event")
    label("orderPreparedEvent")
    input {
        messageFrom("orders-prepared")
        messageBody([
                orderId: 10
        ])
        messageHeaders {
            header("barista", "Jane Doe")
        }
        assertThat('customerServiceHandlesMessage()')
    }
}