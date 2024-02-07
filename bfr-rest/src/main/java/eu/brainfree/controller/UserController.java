package eu.brainfree.controller;

import eu.brainfree.domain.user.UserResponseTO;
import eu.brainfree.logic.mapper.UserMapper;
import eu.brainfree.logic.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Max on 11.12.2021
 * @project brainfree
 * @package controller
 **/

@RestController
@RequestMapping(path = "${rest.base-path}/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;

    private final UserMapper userMapper;

    @GetMapping("/user")
    @PreAuthorize("hasRole('client_user')")
    public ResponseEntity<UserResponseTO> profileOfCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserResponseTO userResponseTO = authenticationService.getUserProfileOfLoggedUser(authentication);
        return ResponseEntity.ok(userResponseTO);
    }
}
