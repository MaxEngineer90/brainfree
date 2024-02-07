package eu.brainfree.logic.service.user;


import eu.brainfree.domain.user.UserResponseTO;
import eu.brainfree.logic.service.cart.CartBackendService;
import eu.brainfree.model.User;
import eu.brainfree.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBackendService {

    private final UserRepository userRepository;
    private final CartBackendService cartBackendService;


    public User getOrCreateUser(final User user) {
        Optional<User> userResult = userRepository.findByEmail(user.getEmail());
        return userResult.orElseGet(() -> createAndGetUser(user));
    }

    private User createAndGetUser(final User user) {
        User result = userRepository.save(user);
        cartBackendService.getOrCreateShoppingCart(user);
        return result;
    }
}
