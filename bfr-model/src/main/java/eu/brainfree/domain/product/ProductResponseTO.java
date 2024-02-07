package eu.brainfree.domain.product;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

/**
 * @author Max on 01.01.2022
 * @project brainfree
 * @package
 **/


@Builder
@Jacksonized
public record ProductResponseTO(
        String name,
        String description,
        String priceAccording,
        BigDecimal price,
        String category,
        String articleNumber
) {
}
