import org.springframework.cloud.contract.spec.Contract
import org.springframework.cloud.contract.spec.internal.HttpMethods
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

Contract.make {
    description("should return the cat by name")
    request {
        url("/cats/Toby")
        method(HttpMethods.HttpMethod.GET.methodName)
    }
    response {
        status(HttpStatus.OK.value())
        body([name: "Toby"])
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }

    }
}