package eu.brainfree.domain.cart;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * @author Max on 01.01.2022
 * @project brainfree
 * @package
 **/


@Builder
@Jacksonized
public record CartRequestTO(String id, String name, List<String> items) {
}


