package eu.jitpay.user.service.dto;

import eu.jitpay.user.domain.Location;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String userId;
    private String firstName;
    private String secondName;
    private String email;
    private LocalDateTime createdOn;
    private List<LocationDTO> locations;
    private Location lastLocation;
}
