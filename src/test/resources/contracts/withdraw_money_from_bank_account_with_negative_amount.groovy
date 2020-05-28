package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should deposit money and return no content"

    request {
        url "/withdraw"
        method PUT()
        body (
                accountId: 1,
                amount: -100
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status BAD_REQUEST()
        body(
                amount: "must be greater than 0"
        )
        headers {
            contentType(applicationJson())
        }
    }
}