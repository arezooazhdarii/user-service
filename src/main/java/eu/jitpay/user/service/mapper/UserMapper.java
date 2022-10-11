package eu.jitpay.user.service.mapper;

import eu.jitpay.user.domain.User;
import eu.jitpay.user.service.dto.UserDTO;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User entity);
    List<UserDTO> toDto(List<User> entities);
    User toEntity(UserDTO dto);
    List<User> toEntity(List<UserDTO> dtos);
}