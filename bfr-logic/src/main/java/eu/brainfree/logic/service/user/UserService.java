package eu.brainfree.logic.service.user;

import eu.brainfree.domain.user.UserResponseTO;
import eu.brainfree.logic.exception.user.UserMailNotVerifiedException;
import eu.brainfree.logic.mapper.JwtToUserMapper;
import eu.brainfree.logic.mapper.UserMapper;
import eu.brainfree.logic.service.cart.CartBackendService;
import eu.brainfree.model.User;
import eu.brainfree.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserBackendService userBackendService;

    private final JwtToUserMapper jwtToUserMapper;
    private final UserMapper userMapper;

    public UserResponseTO getOrCreateUser(final Jwt jwt) {
        String email = jwt.getClaim("email");
        if (isUserMailNotVerified(jwt)) {
            logger.warn(String.format("Mail: %s is not verified", email));
            throw new UserMailNotVerifiedException(email);
        }

        User user = jwtToUserMapper.jwtToUser(jwt);
        user = userBackendService.getOrCreateUser(user);
        return userMapper.toDto(user);
    }

    public User getUserProfile(final Jwt jwt) {
        User user = jwtToUserMapper.jwtToUser(jwt);
        return userBackendService.getOrCreateUser(user);
    }

    private boolean isUserMailNotVerified(final Jwt jwt) {
        return jwt.getClaim("email_verified");
    }

}
