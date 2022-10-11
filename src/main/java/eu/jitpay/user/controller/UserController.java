package eu.jitpay.user.controller;

import eu.jitpay.user.service.UserService;
import eu.jitpay.user.service.dto.Response;
import eu.jitpay.user.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    /**
     * This endpoint allows to create a user.
     *
     * @param userDTO - to create
     * @return - the created user
     */
    @PostMapping(value = "/users")
    public ResponseEntity<Response<UserDTO>> save(@RequestBody UserDTO userDTO,
                                                  HttpServletRequest request) {
        UserDTO user = userService.save(userDTO);
        return new ResponseEntity<>(new Response<UserDTO>()
                .build()
                .setMessage(user)
                .setPath(request.getRequestURI()), HttpStatus.OK);
    }

    /**
     * This endpoint allows to update a user
     *
     * @param userDTO - to update
     * @param userId - to find user
     * @return - the updated user
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<Response<UserDTO>> update(@PathVariable("id") String userId,
                                                    @RequestBody UserDTO userDTO,
                                                    HttpServletRequest request) {
        UserDTO user = userService.update(userDTO,userId);
        return new ResponseEntity<>(new Response<UserDTO>()
                .build()
                .setMessage(user)
                .setPath(request.getRequestURI()), HttpStatus.OK);
    }

}
