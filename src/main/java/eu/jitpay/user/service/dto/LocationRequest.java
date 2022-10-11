package eu.jitpay.user.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationRequest {
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("location")
    private LocationDTO locationDTO;
}
