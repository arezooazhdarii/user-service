package eu.jitpay.user.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {

    @JsonProperty("latitude")
    public Double latitude;

    @JsonProperty("longitude")
    public Double longitude;
}
