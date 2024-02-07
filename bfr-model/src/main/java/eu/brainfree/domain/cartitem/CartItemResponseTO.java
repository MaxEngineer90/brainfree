package eu.brainfree.domain.cartitem;

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
public record CartItemResponseTO(
        String id,
        String name,
        String description,
        String articleNumber,
        String priceAccording,
        BigDecimal price,
        int quantity
) {
}
