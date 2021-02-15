package info.honzamusil.auth0

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
@RequestMapping(path = ["api"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin("*")
class ApiController {

    @GetMapping("/public")
    public fun getPublic(): Mono<String> {
        return Mono.just("Hello public")
    }

    @GetMapping("/private")
    public fun getPrivate(): Mono<String> {
        return Mono.just("Hello private")
    }
}