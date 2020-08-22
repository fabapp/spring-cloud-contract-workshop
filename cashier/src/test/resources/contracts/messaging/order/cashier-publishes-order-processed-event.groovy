package contracts.messaging.order

org.springframework.cloud.contract.spec.Contract.make {
    // Human readable description
    description 'Cashier publishes OrderPlacedEvent'
    // Label by means of which the output message can be triggered
    label 'orderPlacedEvent'
    // input to the contract
    input {
        // the contract will be triggered by a method
        triggeredBy('publishOrderPlacedEvent()')
    }
    // output message of the contract
    outputMessage {
        // destination to which the output message will be sent
        sentTo('orders-placed')
        // the body of the output message
        body('''{ "orderId": 1, "product" : "coffee", "qty":2 }''')
        // the headers of the output message
//        headers {
//            header('BOOK-NAME', 'foo')
//        }
    }
}