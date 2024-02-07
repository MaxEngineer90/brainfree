package eu.brainfree.domain.user;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

/**
 * @author Max on 01.01.2022
 * @project brainfree
 * @package
 **/

@Builder
@Jacksonized
public record UserResponseTO(String firstName, String lastName, String email) {
}
