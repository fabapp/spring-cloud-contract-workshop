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
                qty: 2)
    }



    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                id: 1,
                product: "coffee",
                qty: 2,
                amount: 2.86
        )
    }
}