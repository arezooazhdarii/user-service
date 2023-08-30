package eu.jitpay.user.controller;

import eu.jitpay.user.service.LocationService;
import eu.jitpay.user.service.dto.LocationRequest;
import eu.jitpay.user.service.dto.LocationResponse;
import eu.jitpay.user.service.dto.LocationsResponse;
import eu.jitpay.user.service.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class LocationController {
    private final LocationService locationService;

    /**
     * This endpoint allows get user data with the latest location
     *
     * @param userId - to get user latest location
     * @return - the user location information
     */
//    @GetMapping("/locations/{id}")
//    public ResponseEntity<Response<LocationResponse>> getLastLocation(@PathVariable("id") String userId,
//                                                                      HttpServletRequest request) {
//        LocationResponse location = locationService.getUserLastLocation(userId);
//        return new ResponseEntity<>(new Response<LocationResponse>()
//                .build()
//                .setMessage(location)
//                .setPath(request.getRequestURI()), HttpStatus.OK);
//    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<LocationResponse> getLastLocation(@PathVariable("id") String userId,
                                                                      HttpServletRequest request) {
        LocationResponse location = locationService.getUserLastLocation(userId);
        return new ResponseEntity<>(location,HttpStatus.OK);
    }

    /**
     * This endpoint allows get user locations by Two range Date
     *
     * @param userId    - to get user locations
     * @param startDate - to get startDate
     * @param endDate   - to get endDate
     * @return - the user location information
     */
    @GetMapping("/locationsByDate/{id}")
    public ResponseEntity<Response<LocationsResponse>> getLocationsByDateTimeRange(@PathVariable("id") String userId,
                                                                                   @RequestParam(name = "startDate") LocalDateTime startDate,
                                                                                   @RequestParam(name = "endDate") LocalDateTime endDate,
                                                                                   @RequestParam(name = "page") int page,
                                                                                   @RequestParam(name = "size") int size,
                                                                                   HttpServletRequest request) {
        LocationsResponse locations = locationService.getLocationsByDateTimeRange(userId, startDate, endDate, page, size);
        return new ResponseEntity<>(new Response<LocationsResponse>()
                .build()
                .setMessage(locations)
                .setPath(request.getRequestURI()), HttpStatus.OK);
    }

    /**
     * This endpoint allows save user location
     *
     * @param locationRequest - to save user location
     * @return - ok
     */
    @PostMapping("/locations")
    public ResponseEntity<Response<String>> saveLocation(@RequestBody LocationRequest locationRequest,
                                                         HttpServletRequest request) {
        locationService.saveLocation(locationRequest);
        return new ResponseEntity<>(new Response<String>()
                .build()
                .setMessage("OK")
                .setPath(request.getRequestURI()), HttpStatus.OK);
    }

}
