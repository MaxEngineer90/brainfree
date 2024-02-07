package eu.brainfree.logic.mapper;

import eu.brainfree.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtToUserMapper {

    public User jwtToUser(final Jwt jwt) {
        String email = jwt.getClaim("email");
        UUID uuid = UUID.fromString(jwt.getClaim("sid"));
        String givenName = jwt.getClaim("given_name");
        String familyName = jwt.getClaim("family_name");


        return User
                .builder()
                .firstName(givenName)
                .lastName(familyName)
                .email(email)
                .id(uuid)
                .build();
    }

}
