package contracts.rest.payment


org.springframework.cloud.contract.spec.Contract.make {
    description "should accept payment for order and return payment details."

    request {
        url "/order/1/payment"
        method POST()
        headers {
            contentType applicationJson()
        }
        body(amountGiven: 3)
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
            amountAsked: 2.86,
            amountGiven: 3,
            changeReturned: 0.14
        )
    }
}