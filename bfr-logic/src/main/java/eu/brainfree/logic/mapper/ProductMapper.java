package eu.brainfree.logic.mapper;

import eu.brainfree.domain.product.ProductResponseTO;
import eu.brainfree.domain.product.UpdateProductTO;
import eu.brainfree.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author Max on 01.01.2022
 * @project brainfree
 * @package
 **/

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {

    ProductResponseTO toDto(Product product);

    @Mapping(target = "articleNumber", source = "articleNumber")
    Product toEntity(UpdateProductTO dto, String articleNumber);
}
