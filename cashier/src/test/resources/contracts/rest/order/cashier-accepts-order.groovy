package contracts.rest.order

org.springframework.cloud.contract.spec.Contract.make {
    description "should accept order and return processed order."

    request {
        url "/order"
        method POST()
        headers {
            contentType applicationJson()
        }
        body(product: "coffee",
                mobileNumber: $(
                        consumer(regex("\\+49 ([1-9]{1}[0-9]{2}) ([0-9]{7})")),
                        producer("+49 160 5563477")
                ),
                qty: 2
        )
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                id: 1,
                product: "coffee",
                qty: fromRequest().body('$.qty'),
                amount: 2.86,
                mobileNumber: fromRequest().body('$.mobileNumber'),
                timeOrdered: $(
                        consumer("2016-12-31T23:30:59Z"),
                        producer(iso8601WithOffset())
                )
        )
    }
}
