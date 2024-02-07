package eu.brainfree.domain.cart;

import eu.brainfree.domain.cartitem.CartItemResponseTO;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Max on 01.01.2022
 * @project brainfree
 * @package
 **/


@Builder
@Jacksonized
public record CartResponseTO(String id, String name, List<CartItemResponseTO> items, BigDecimal priceSum) {

}

