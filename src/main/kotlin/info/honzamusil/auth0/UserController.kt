package info.honzamusil.auth0

import info.honzamusil.auth0.authorization.JwtService
import info.honzamusil.auth0.model.User
import info.honzamusil.auth0.model.UserCredentials
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.security.Principal

@RestController
@RequestMapping(path = ["user"], produces = [MediaType.APPLICATION_JSON_VALUE])
@CrossOrigin("*")
class UserController(private val jwtService: JwtService) {

    // In memory user storage :)
    private val users: MutableMap<String, UserCredentials> = mutableMapOf(
        "honzamusil@honzamusil.info" to UserCredentials("honzamusil@honzamusil.info", "honzamusil")
    )

    @PutMapping("/signup")
    fun signUp(@RequestBody user: UserCredentials): Mono<ResponseEntity<Void>> {
        users[user.email] = user
        return Mono.just(ResponseEntity.noContent().build())
    }

    @PostMapping("/login")
    fun login(@RequestBody user: UserCredentials): Mono<ResponseEntity<Void>> {
        return Mono.justOrEmpty(users[user.email])
            .filter { it.password == user.password }
            .map {
                val jwt = jwtService.createJwt(it.email)
                val authCookie = ResponseCookie.fromClientResponse("X-Auth", jwt)
                    .maxAge(3600)
                    .httpOnly(true)
                    .path("/")
                    .secure(false) // should be true in production
                    .build()

                ResponseEntity.noContent()
                    .header("Set-Cookie", authCookie.toString())
                    .build<Void>()
            }
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
    }

    @GetMapping
    fun getLoggedUserData(principal: Principal): Mono<ResponseEntity<User>> {
        return Mono.justOrEmpty(users[principal.name])
            .map { ResponseEntity.ok(User(it.email)) }
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))

    }
}