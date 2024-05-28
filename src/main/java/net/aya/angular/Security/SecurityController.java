package net.aya.angular.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

//consulter utilisateur authentifie
@RestController
@RequestMapping("/auth")
public class SecurityController {
    @Autowired
    private AuthenticationManager authenficiationManager;
    @Autowired
    private JwtEncoder jwtEncoder;
    @GetMapping("/user")
    public Authentication authentication(Authentication authentication) {
        return authentication;

    }
    @PostMapping("/login")
    public Map<String,String> login(String username,String password){

Authentication authentication = authenficiationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        Instant instant=Instant.now();
        // gernee les roles
      String scope=  authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(instant).
                expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                .subject(username)
                .claim("scope",scope)
                  .build();
        JwtEncoderParameters parameters = JwtEncoderParameters
                .from(JwsHeader.with(MacAlgorithm.HS512).build(),claims);
        String jwt=jwtEncoder.encode(parameters).getTokenValue();
        return Map.of("access-token",jwt);
    }
}
