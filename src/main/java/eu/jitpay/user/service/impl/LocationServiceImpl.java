package eu.jitpay.user.service.impl;

import eu.jitpay.user.domain.Location;
import eu.jitpay.user.domain.User;
import eu.jitpay.user.exception.NotFoundException;
import eu.jitpay.user.repository.LocationRepository;
import eu.jitpay.user.service.LocationService;
import eu.jitpay.user.service.dto.*;
import eu.jitpay.user.service.mapper.LocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final UserServiceImpl userService;

    /**
     * This method used to find the latest location
     *
     * @param userId the userId of the location
     */
    @Override
    public LocationResponse getUserLastLocation(String userId) {
        Boolean isUserExist = userService.isUserExist(userId);
        if (!isUserExist) throw new NotFoundException("not found user");

        return locationRepository.findFirstByUser_UserIdOrderByCreatedOnDesc(userId).map(location -> {
            LocationDTO locationDTO = locationMapper.toDto(location);
            LocationResponse locationResponse = new LocationResponse();
            locationResponse.setLocation(locationDTO);
            locationResponse.setUserId(location.getUser().getUserId());
            locationResponse.setFirstName(location.getUser().getFirstName());
            locationResponse.setSecondName(location.getUser().getSecondName());
            locationResponse.setEmail(location.getUser().getEmail());
            locationResponse.setCreatedOn(location.getUser().getCreatedOn());
            return locationResponse;
        }).orElseThrow(() -> new NotFoundException("user doesn't have location"));
    }

    /**
     * This method used to find the locations between two ranges
     *
     * @param userId    the userId of the location
     * @param startDate the start date of createdOn
     * @param endDate   the end date of createdOn
     */
    @Override
    public LocationsResponse getLocationsByDateTimeRange(String userId, LocalDateTime startDate, LocalDateTime endDate,
                                                         int page, int size) {
        Boolean isUserExist = userService.isUserExist(userId);
        if (!isUserExist) throw new NotFoundException("user isn't exist");

        Pageable pageable = PageRequest.of(page, size);

        Page<Location> byCreatedOnBetween = locationRepository.findByUser_UserIdAndCreatedOnBetween(userId, startDate, endDate, pageable);

        if (byCreatedOnBetween.isEmpty() || byCreatedOnBetween.getContent().size() == 0)
            return LocationsResponse.builder()
                    .locations(null)
                    .userId(userId).build();

        List<Locations> locations = byCreatedOnBetween.getContent().stream().map(this::mapLocationEntityToDTO).collect(Collectors.toList());
//        List<Locations> locations = byCreatedOnBetween.getContent().stream()
//                .map(this::mapLocationEntityToDTO).toList;
        return LocationsResponse.builder()
                .locations(locations)
                .userId(userId).build();
    }


    /**
     * This method used to save location the requested stock
     *
     * @param locationRequest the location to create
     */
    @Override
    public void saveLocation(LocationRequest locationRequest) {
        User user = userService.findById(locationRequest.getUserId());
        Location location = Location.builder()
                .latitude(locationRequest.getLocationDTO().getLatitude())
                .longitude(locationRequest.getLocationDTO().getLongitude())
                .user(user).build();
        locationRepository.save(location);
    }

    Locations mapLocationEntityToDTO(Location location) {
        if (location == null) {
            return null;
        }
        LocationDTO locationDTO = LocationDTO.builder()
                .longitude(location.getLongitude())
                .latitude(location.getLatitude()).build();

        return Locations.builder()
                .createdOn(location.getCreatedOn())
                .location(locationDTO).build();
    }

}
