package eu.jitpay.user.service.impl;

import eu.jitpay.user.domain.Location;
import eu.jitpay.user.domain.User;
import eu.jitpay.user.exception.NotFoundException;
import eu.jitpay.user.repository.LocationRepository;
import eu.jitpay.user.service.dto.LocationDTO;
import eu.jitpay.user.service.dto.LocationRequest;
import eu.jitpay.user.service.dto.LocationResponse;
import eu.jitpay.user.service.dto.LocationsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class LocationServiceImplTest {

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private LocationServiceImpl locationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("get location should get when everything is ok")
    void getUserLastLocation_shouldGet_whenEverythingIsOk() {
        User user = createUser();
        String userId ="14e7ddc5-a3ef-4932-b150-66c40e826688";
        LocationResponse locationResponse = createLocationResponse();
        Mockito.when(userService.isUserExist(userId)).thenReturn(true);
        Optional<Location> optionalLocation = getLocation();
        Mockito.doReturn(optionalLocation).when(this.locationRepository).findFirstByUser_UserIdOrderByCreatedOnDesc(Mockito.any());
        LocationResponse location = locationService.getUserLastLocation(userId);

        Assertions.assertNotNull(location);
        Assertions.assertEquals("14e7ddc5-a3ef-4932-b150-66c40e826688", location.getUserId());
        Assertions.assertEquals(1234.1234, locationResponse.getLocation().getLongitude());
        Assertions.assertEquals(5678.5678, locationResponse.getLocation().getLatitude());

        Mockito.verify(locationRepository, Mockito.times(1)).findFirstByUser_UserIdOrderByCreatedOnDesc(userId);
    }

    @Test
    @DisplayName("get location should throw exception when user doesn't exist")
    void getUserLastLocation_shouldThrowNotFoundException_whenUserNotExist() {
        String userId ="14e7ddc5-a3ef-4932-b150-66c40e826688";
        Mockito.when(userService.isUserExist(userId)).thenReturn(false);
        Mockito.doThrow(new NotFoundException("not found user")).when(userService).isUserExist(userId);

        Assertions.assertThrows(NotFoundException.class, () -> this.locationService.getUserLastLocation(userId));
    }


    @Test
    @DisplayName("get location should throw exception when user location doesn't exist")
    void getUserLastLocation_shouldThrowNotFoundException_whenLocationNotExist() {
        User user = createUser();
        String userId ="14e7ddc5-a3ef-4932-b150-66c40e826688";
        Mockito.when(userService.findById(userId)).thenReturn(user);

        Mockito.doThrow(new NotFoundException("user doesn't have location")).when(locationRepository)
                .findFirstByUser_UserIdOrderByCreatedOnDesc(user.getUserId());

        Assertions.assertThrows(NotFoundException.class, () -> this.locationService.getUserLastLocation(userId));
    }


    @Test
    @DisplayName("get locations by range date range get when everything is ok")
    void getLocationsByDateTimeRange_shouldGet_whenEverythingIsOk() {
        String userId ="14e7ddc5-a3ef-4932-b150-66c40e826688";

        Mockito.when(userService.isUserExist(userId)).thenReturn(true);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Location> locations = createLocations();

        Mockito.doReturn(locations).when(this.locationRepository)
                .findByUser_UserIdAndCreatedOnBetween(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any());
        LocationsResponse location = locationService.getLocationsByDateTimeRange(userId,
                LocalDate.of(2021, 1, 1).atStartOfDay(),
                LocalDate.of(2022, 12, 1).atStartOfDay(), 0, 10);

        Assertions.assertNotNull(location);
        Assertions.assertEquals("14e7ddc5-a3ef-4932-b150-66c40e826688", location.getUserId());
        Assertions.assertEquals(location.getLocations().size(),3);

        Mockito.verify(locationRepository, Mockito.times(1))
                .findByUser_UserIdAndCreatedOnBetween(userId,
                        LocalDate.of(2021, 1, 1).atStartOfDay(),
                        LocalDate.of(2022, 12, 1).atStartOfDay(),pageable);
    }

    @Test
    @DisplayName("get locations by range date range get page Empty")
    void getLocationsByDateTimeRange_shouldGet_whenPageIsEmpty() {
        String userId ="14e7ddc5-a3ef-4932-b150-66c40e826688";

        Mockito.when(userService.isUserExist(userId)).thenReturn(true);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Location> locations = Page.empty();

        Mockito.doReturn(locations).when(this.locationRepository)
                .findByUser_UserIdAndCreatedOnBetween(Mockito.any(),Mockito.any(),Mockito.any(),Mockito.any());
        LocationsResponse location = locationService.getLocationsByDateTimeRange(userId,
                LocalDate.of(2021, 1, 1).atStartOfDay(),
                LocalDate.of(2022, 12, 1).atStartOfDay(), 0, 10);

        Assertions.assertNotNull(location);
        Assertions.assertEquals("14e7ddc5-a3ef-4932-b150-66c40e826688", location.getUserId());
        Assertions.assertEquals(location.getLocations(),null);

        Mockito.verify(locationRepository, Mockito.times(1))
                .findByUser_UserIdAndCreatedOnBetween(userId,
                        LocalDate.of(2021, 1, 1).atStartOfDay(),
                        LocalDate.of(2022, 12, 1).atStartOfDay(),pageable);
    }

    @Test
    @DisplayName("get locations by range date range should throw exception when user doesn't exist")
    void getLocationsByDateTimeRange_shouldThrowNotFoundException_whenUserNotExist() {
        String userId ="14e7ddc5-a3ef-4932-b150-66c40e826688";
        Mockito.when(userService.findById(userId)).thenReturn(null);
        Mockito.doThrow(new NotFoundException("not found user")).when(userService).isUserExist(userId);

        Assertions.assertThrows(NotFoundException.class, () ->
                this.locationService.getLocationsByDateTimeRange(userId,LocalDate.now().atStartOfDay(),LocalDate.now().atStartOfDay(),0,1));
    }

    @Test
    @DisplayName("save location should save when everything is ok")
    void saveLocation_shouldSave_whenEverythingIsOk() {
        User user = createUser();
        LocationRequest locationRequest = createLocationRequest();
        Location location = createLocation(locationRequest, user);
        Mockito.when(userService.findById(user.getUserId())).thenReturn(user);
        Mockito.doReturn(location).when(this.locationRepository).save(Mockito.any());
        locationService.saveLocation(locationRequest);
        Mockito.verify(this.locationRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    @DisplayName("save location should throw exception when user doesn't exist")
    void saveLocation_shouldThrowNotFoundException_whenUserNotExist() {
        User user = createUser();
        LocationRequest locationRequest = createLocationRequest();
        Mockito.when(userService.findById(user.getUserId())).thenReturn(null);
        Mockito.doThrow(new NotFoundException("not found user")).when(userService).findById(user.getUserId());

        Assertions.assertThrows(NotFoundException.class, () -> this.locationService.saveLocation(locationRequest));
    }

    User createUser() {
        return User.builder()
                .userId("14e7ddc5-a3ef-4932-b150-66c40e826688")
                .firstName("Arezoo")
                .secondName("Azhdari")
                .email("arezooazhdarii@gmail.com").build();
    }
    LocationRequest createLocationRequest() {
        LocationDTO locationDTO = LocationDTO.builder().longitude(1234.1234).latitude(5678.5678).build();
        return LocationRequest.builder()
                .userId("14e7ddc5-a3ef-4932-b150-66c40e826688")
                .locationDTO(locationDTO).build();
    }

    Location createLocation(LocationRequest locationRequest,User user){
        return Location.builder()
                .latitude(locationRequest.getLocationDTO().getLatitude())
                .longitude(locationRequest.getLocationDTO().getLongitude())
                .user(user).build();
    }

    LocationResponse createLocationResponse(){
        LocationDTO locationDTO = LocationDTO.builder().longitude(1234.1234).latitude(5678.5678).build();
        return LocationResponse.builder()
                .userId("14e7ddc5-a3ef-4932-b150-66c40e826688")
                .firstName("Arezoo")
                .secondName("Azhdari")
                .email("arezooazhdarii@gmail.com")
                .createdOn(LocalDate.now().atStartOfDay())
                .location(locationDTO).build();
    }

    Optional<Location> getLocation(){
        return Optional.ofNullable(Location.builder()
                .latitude(1234.1234)
                .longitude(5678.5678)
                .createdOn(LocalDate.now().atStartOfDay())
                .user(createUser()).build());
    }

   Page<Location> createLocations(){
       List<Location> locations = new ArrayList<>();
       Location location1 = Location.builder().latitude(12.12).longitude(56.56).
               createdOn(LocalDate.of(2020, 9, 1).atStartOfDay()).user(createUser()).build();
       Location location2 = Location.builder().latitude(34.34).longitude(78.78).
               createdOn(LocalDate.of(2021, 9, 1).atStartOfDay()).user(createUser()).build();
       Location location3 = Location.builder().latitude(14.14).longitude(58.58).
               createdOn(LocalDate.now().atStartOfDay()).user(createUser()).build();
       locations.add(location1);
       locations.add(location2);
       locations.add(location3);
       return new PageImpl<>(locations);
    }

}