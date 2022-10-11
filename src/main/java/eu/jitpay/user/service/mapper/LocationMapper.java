package eu.jitpay.user.service.mapper;

import eu.jitpay.user.domain.Location;
import eu.jitpay.user.service.dto.LocationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDto(Location entity);
    List<LocationDTO> toDto(List<Location> entities);
    Location toEntity(LocationDTO dto);
    List<Location> toEntity(List<LocationDTO> dtos);
}