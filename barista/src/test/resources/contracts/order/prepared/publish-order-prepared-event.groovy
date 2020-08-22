package contracts.order.prepared

org.springframework.cloud.contract.spec.Contract.make {
    // Human readable description
    description 'Barista publishes OrderPreparedEvent'
    // Label by means of which the output message can be triggered
    label 'orderPreparedEvent'
    // input to the contract
    input {
        // the contract will be triggered by a method
        triggeredBy('publishOrderPreparedEvent()')
    }
    // output message of the contract
    outputMessage {
        // destination to which the output message will be sent
        sentTo('orders-prepared')
        // the body of the output message
        body('''{ "orderId" : "1" }''')
        // the headers of the output message
        headers {
            header('barista', 'Jane Doe')
        }
    }
}