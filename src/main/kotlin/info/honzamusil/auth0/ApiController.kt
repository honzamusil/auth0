package info.honzamusil.auth0

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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