package info.honzamusil.auth0

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Auth0Application

fun main(args: Array<String>) {
	runApplication<Auth0Application>(*args)
}
