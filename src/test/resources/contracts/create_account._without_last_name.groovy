package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should not create account without last name and return bad request"

    request {
        url "/accounts"
        method POST()
        body (
                lastName: "Tomasz"
        )
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status BAD_REQUEST()
        headers {
            contentType applicationJson()
        }
        body (
                firstName: "must not be blank"
        )
    }
}