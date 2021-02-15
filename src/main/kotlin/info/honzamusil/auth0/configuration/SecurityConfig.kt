package info.honzamusil.auth0.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun configure(http: ServerHttpSecurity): SecurityWebFilterChain? {
        return http.authorizeExchange()
            .pathMatchers("/api/public").permitAll()
            .pathMatchers("/api/private").authenticated()
            .and().formLogin()
            .and().cors().and().build()
    }
}