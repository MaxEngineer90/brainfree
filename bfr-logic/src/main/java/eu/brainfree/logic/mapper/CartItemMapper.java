package eu.brainfree.logic.mapper;

import eu.brainfree.domain.cartitem.CartItemResponseTO;
import eu.brainfree.model.Cart;
import eu.brainfree.model.CartItem;
import eu.brainfree.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author Max on 01.01.2022
 * @project brainfree-webapp
 * @package
 **/

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CartItemMapper {


    CartItemResponseTO toDto(CartItem cartItem);

    
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    CartItem toEntity(int quantity, Cart cart, Product product);
}
