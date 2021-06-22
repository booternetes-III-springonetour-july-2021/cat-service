import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description("should return the cat by name")
    request {
        url("/cats/Toby")
        method(GET())
    }
    response {
        status(OK())
        body([name: "Toby"])
        headers {
            contentType(applicationJson())
        }

    }
}