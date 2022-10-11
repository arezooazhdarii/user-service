package eu.jitpay.user.service;

import eu.jitpay.user.service.dto.LocationRequest;
import eu.jitpay.user.service.dto.LocationResponse;
import eu.jitpay.user.service.dto.LocationsResponse;

import java.time.LocalDateTime;

public interface LocationService {
    LocationResponse getUserLastLocation(String userId);
    LocationsResponse getLocationsByDateTimeRange(String userId, LocalDateTime startDate, LocalDateTime endDate, int page, int size);
    void saveLocation(LocationRequest locationRequest);
}
