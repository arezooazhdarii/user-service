package eu.jitpay.user.service.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationResponse {
    private String userId;
    private String firstName;
    private String secondName;
    private String email;
    private LocalDateTime createdOn;
    private LocationDTO location;
}
