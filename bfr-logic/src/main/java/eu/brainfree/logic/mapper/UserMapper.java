package eu.brainfree.logic.mapper;

import eu.brainfree.domain.user.UserResponseTO;
import eu.brainfree.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Max on 01.01.2022
 * @project brainfree
 * @package
 **/

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserResponseTO toDto(User user);
}
