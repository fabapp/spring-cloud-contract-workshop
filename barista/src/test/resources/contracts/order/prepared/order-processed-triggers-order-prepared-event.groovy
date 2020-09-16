package contracts.order.prepared;

org.springframework.cloud.contract.spec.Contract.make {
    name "order-processed-triggers-order-prepared-event"
    description """
        Inbound messages on orders-placed topic are processed and
        outbound message is sent to orders-prepared topic.
    """
    label("orderProcessed")
    input {
        messageFrom("orders-placed")
        messageBody([
                orderId: 1,
                product: "coffee",
                qty: 2
        ])
    }
    outputMessage {
        sentTo("orders-prepared")
        body([
            orderId: 1
        ])
        headers {
            header("barista","Jane Doe")
        }
    }
}