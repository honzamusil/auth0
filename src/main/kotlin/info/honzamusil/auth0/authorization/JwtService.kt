package info.honzamusil.auth0.authorization

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.security.KeyPair
import java.time.Duration
import java.time.Instant
import java.util.*

@Service
class JwtService {
    val signatureAlgorithm = SignatureAlgorithm.RS256
    val keyPair: KeyPair = Keys.keyPairFor(signatureAlgorithm)

    fun createJwt(email: String): String {
        return Jwts.builder()
            .signWith(keyPair.private, signatureAlgorithm)
            .setSubject(email)
            .setIssuer("identity")
            .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(15))))
            .setIssuedAt(Date.from(Instant.now()))
            .compact()
    }

    fun validateJwt(jwt: String): Jws<Claims> {
        return Jwts.parserBuilder()
            .setSigningKey(keyPair.public)
            .build()
            .parseClaimsJws(jwt)
    }
}