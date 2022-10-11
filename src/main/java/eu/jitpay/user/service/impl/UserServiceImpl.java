package eu.jitpay.user.service.impl;


import eu.jitpay.user.domain.User;
import eu.jitpay.user.exception.NotFoundException;
import eu.jitpay.user.exception.UserAlreadyExistException;
import eu.jitpay.user.repository.UserRepository;
import eu.jitpay.user.service.UserService;
import eu.jitpay.user.service.dto.UserDTO;
import eu.jitpay.user.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * This method used to save user in the database
     *
     * @param userDTO the user to create
     * @return the created user
     */
    @Override
    public UserDTO save(UserDTO userDTO) {
        boolean isUserExist = userRepository.existsByEmail(userDTO.getEmail());
        if (isUserExist)
            throw new UserAlreadyExistException("user already exist");
        User user = userMapper.toEntity(userDTO);
        user= userRepository.save(user);
        return userMapper.toDto(user);
    }

    /**
     * This method used to update user
     *
     * @param userId       the id of the user
     * @param userDTO the new user what should set for user
     * @return the updated user
     */
    @Override
    public UserDTO update(UserDTO userDTO, String userId) {
        return userRepository.findById(userId).map(user -> {
            user.setSecondName(userDTO.getSecondName());
            user.setFirstName(userDTO.getFirstName());
            user.setEmail(userDTO.getEmail());
            userRepository.save(user);
            return userDTO;
        }).orElseThrow(() -> new NotFoundException("User not found"));
    }

    /**
     * This method return the user
     *
     * @param id the id of user
     * @return the user
     */
    public User findById(String id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("not found user"));
    }

    public Boolean isUserExist(String userId){
        return userRepository.existsById(userId);
    }

}
