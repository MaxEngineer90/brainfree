package eu.brainfree.logic.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserMailNotVerifiedException extends RuntimeException{
    public UserMailNotVerifiedException(String email) {
        super(String.format("Mail: %s is not verified", email));
    }
}
