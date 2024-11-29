package hr.server.serverhr.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class jwtService {
    private static final String SECRET_KEY ="WC7Li53hJQCieIi328NCmhbz3aFnrKISunAtOr1atZLkrno6KxAiGoEwtzunolmz";

    private final UserDetailsService userDetailsService;

    public jwtService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    public String extractUsername(String jwt) {

        return extractclaim(jwt,Claims::getSubject);
    }

    public <T> T extractclaim(String token, Function<Claims,T>claimsTResolver){
        final Claims claims=extractallClaims(token);
        return claimsTResolver.apply(claims);

    }
    private Claims extractallClaims(String token){
        return Jwts.parserBuilder().
                setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private  Key getSignInKey() {
        byte[] keybytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keybytes);

    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public  String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 2 * 24 * 60 * 60 * 4000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenValiddd(String token){
        return  !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractclaim(token,Claims::getExpiration);
    }

    public UserDetails getUserFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        return userDetailsService.loadUserByUsername(username);
    }

}
