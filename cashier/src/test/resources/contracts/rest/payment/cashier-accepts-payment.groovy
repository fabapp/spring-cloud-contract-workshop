package contracts.rest.payment


org.springframework.cloud.contract.spec.Contract.make {
    description "should accept payment for order and return payment details."

    request {
        url $(
                client(regex("/order/([0-9]+)/payment")),
                server(execute('generateUrl()'))
        )
        method POST()
        headers {
            contentType applicationJson()
        }
        body(
                amountGiven: 3.0
        )
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                amountAsked: 2.86,
                amountGiven: fromRequest().body('$.amountGiven'),
                changeReturned: 0.14
        )
    }
}