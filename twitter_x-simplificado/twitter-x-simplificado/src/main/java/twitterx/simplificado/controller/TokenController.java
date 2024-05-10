package twitterx.simplificado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import twitterx.simplificado.controller.dto.LoginRequest;
import twitterx.simplificado.controller.dto.LoginResponse;
import twitterx.simplificado.repository.UserRepository;

import java.time.Instant;

@RestController
public class TokenController {
    public TokenController(
            UserRepository userRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            JwtEncoder jwtEncoder) {
        this.userRepository =
                userRepository;
        this.bCryptPasswordEncoder =
                bCryptPasswordEncoder;
        this.jwtEncoder =
                jwtEncoder;
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtEncoder
            jwtEncoder;
    public TokenController(JwtEncoder jwtEncoder){
        this.jwtEncoder = jwtEncoder;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());
  if(user.isEmpty()  || !user.get().isLoginCorrect(loginRequest, bCryptPasswordEncoder)){
      throw new BadCredentialsException("Invalid username or password");
  }
  var now =
          Instant.now();
  var expiresIn = 300L;
  var claims =
          JwtClaimsSet.builder()
                  .subject(user.get().getUserId().toString())
                  .subject(user.get().getUsername())
                  .expiresAt(now.plusSeconds(expiresIn))

                  .build();
        var jwtValue = jwtEncoder.encode(
                JwtEncoderParameters.from(claims)).getTokenValue();
        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }


}
