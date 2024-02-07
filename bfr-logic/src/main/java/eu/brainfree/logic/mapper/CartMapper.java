package eu.brainfree.logic.mapper;


import eu.brainfree.domain.cart.CartRequestTO;
import eu.brainfree.domain.cart.CartResponseTO;
import eu.brainfree.domain.cartitem.CartItemResponseTO;
import eu.brainfree.model.Cart;
import eu.brainfree.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * @author Max on 01.01.2022
 * @project brainfree
 * @package
 **/


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "priceSum", expression = "java(priceSum(dtos))")
    CartResponseTO toDto(Cart cart, List<CartItemResponseTO> dtos);

    Cart toEntity(CartRequestTO cartDTO, User user);

    default BigDecimal priceSum(Collection<CartItemResponseTO> items) {
        return items.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
