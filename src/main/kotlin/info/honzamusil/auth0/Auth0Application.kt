package info.honzamusil.auth0

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
class Auth0Application

fun main(args: Array<String>) {
	runApplication<Auth0Application>(*args)
}
