package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should not deposit money if bank account does not exist and return bad request"

    request {
        url "/deposit"
        method PUT()
        body (
                accountId: 1
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status BAD_REQUEST()
        body(
                amount: "must not be null"
        )
        headers {
            contentType(applicationJson())
        }
    }
}