package eu.jitpay.user.service.impl;

import eu.jitpay.user.domain.User;
import eu.jitpay.user.exception.NotFoundException;
import eu.jitpay.user.exception.UserAlreadyExistException;
import eu.jitpay.user.repository.UserRepository;
import eu.jitpay.user.service.dto.UserDTO;
import eu.jitpay.user.service.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private UserMapper userMapper;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("save user should save when everything is ok")
    void saveUser_shouldSave_whenEverythingIsOk() {
        UserDTO userDTO = createUserDTO();
        User saveUser = createUser();

        Mockito.doReturn(false).when(this.userRepository).existsByEmail(userDTO.getEmail());
        Mockito.doReturn(saveUser).when(this.userRepository).save(Mockito.any());
        Mockito.doReturn(userDTO).when(this.userMapper).toDto(saveUser);

        UserDTO returnUser= userService.save(userDTO);

        Assertions.assertNotNull(returnUser);
        Assertions.assertEquals("arezooazhdarii@gmail.com", returnUser.getEmail());
        Assertions.assertEquals("Arezoo", returnUser.getFirstName());

        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail("arezooazhdarii@gmail.com");
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(userMapper, Mockito.times(1)).toDto((User) Mockito.any());
    }

    @Test
    @DisplayName("save user should throw exception when stock already exist")
    void saveUser_shouldThrowUserAlreadyExist_whenUserExist() {
        UserDTO userDTO = createUserDTO();

        Mockito.doReturn(true).when(userRepository).existsByEmail("arezooazhdarii@gmail.com");

        Assertions.assertThrows(UserAlreadyExistException.class, () -> userService.save(userDTO));
        Mockito.verify(userRepository, Mockito.times(1)).existsByEmail("arezooazhdarii@gmail.com");
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
        Mockito.verify(userMapper, Mockito.never()).toDto((User) Mockito.any());
        Mockito.verify(userMapper, Mockito.never()).toEntity(userDTO);
    }

    @Test
    @DisplayName("update user should update when everything is ok")
    void updateUser_shouldUpdate_whenEverythingIsOk() {
        String userId="14e7ddc5-a3ef-4932-b150-66c40e826688";
        UserDTO userDTO = UserDTO.builder()
                .firstName("azar")
                .secondName("azar")
                .email("azar@gmail.com").build();
        User userEntity = createUser();

        Mockito.doReturn(Optional.of(userEntity)).when(userRepository).findById(userId);
        Mockito.doAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]).when(userRepository).save(Mockito.any());

        UserDTO returnUser= userService.update(userDTO,userId);

        Assertions.assertNotNull(returnUser);
        Assertions.assertEquals("azar@gmail.com", returnUser.getEmail());
        Assertions.assertEquals("azar", returnUser.getFirstName());

        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("update user should throw exception when user doesn't exist")
    void updateUser_shouldThrowUserNotFoundException_whenUserExist() {
        String userId="14e7ddc5-a3ef-4932-b150-66c40e826688";
        UserDTO userDTO = createUserDTO();

        Mockito.doReturn(Optional.empty()).when(userRepository).findById(userId);

        Assertions.assertThrows(NotFoundException.class, () -> userService.update(userDTO, userId));

        Mockito.verify(userRepository, Mockito.times(1)).findById(userId);
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    User createUser() {
        return User.builder()
                .userId("14e7ddc5-a3ef-4932-b150-66c40e826688")
                .firstName("Arezoo")
                .secondName("Azhdari")
                .createdOn(LocalDate.now().atStartOfDay())
                .email("arezooazhdarii@gmail.com").build();
    }

    UserDTO createUserDTO() {
        return UserDTO.builder()
                .userId("14e7ddc5-a3ef-4932-b150-66c40e826688")
                .firstName("Arezoo")
                .secondName("Azhdari")
                .email("arezooazhdarii@gmail.com").build();
    }

}