package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should create account and return it"

    request {
        url "/accounts"
        method POST()
        body (
                firstName: "Tomasz",
                lastName: "Kwiecien"
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status CREATED()
        headers {
            contentType applicationJson()
        }
        body (
                id: 2,
                firstName: "Tomasz",
                lastName: "Kwiecien",
                balance: 0
        )
    }
}