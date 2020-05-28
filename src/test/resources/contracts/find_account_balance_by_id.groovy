package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return account by id=1"

    request {
        url "/accounts/1"
        method GET()
    }

    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                balance: 100
        )
    }
}