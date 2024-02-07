package eu.brainfree.logic.service.auth;

import eu.brainfree.domain.user.UserResponseTO;
import eu.brainfree.logic.service.user.UserService;
import eu.brainfree.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * @author Max on 09.09.2022
 * @project brainfree-webapp
 * @date 09.09.2022
 **/

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;

    public UserResponseTO getUserProfileOfLoggedUser(final Authentication authentication) {
        Jwt jwt = getJwt(authentication);
        return userService.getOrCreateUser(jwt);
    }

    public User getUserProfileFromJWT(final Authentication authentication){
        Jwt jwt = getJwt(authentication);
        return userService.getUserProfile(jwt);
    }

    public String getUserNameFromToken(final Authentication authentication) {
        return getJwt(authentication)
                .getClaim("preferred_username");
    }

    private Jwt getJwt(Authentication authentication) {
        return ((JwtAuthenticationToken) authentication).getToken();
    }
}
