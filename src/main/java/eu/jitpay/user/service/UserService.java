package eu.jitpay.user.service;

import eu.jitpay.user.service.dto.UserDTO;

public interface UserService {
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO, String userId);

}
