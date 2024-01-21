package dev.shoxruhjon.instagram_api.mapper;

import dev.shoxruhjon.instagram_api.dto.request.UserCreateDto;
import dev.shoxruhjon.instagram_api.dto.response.UserResponse;
import dev.shoxruhjon.instagram_api.entity.UserEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserEntity userCreateDtoToEntity(UserCreateDto userCreateDto);
    UserCreateDto userEntityToDto(UserEntity userEntity);
    UserResponse userEntityToResponseDto(UserEntity userEntity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserEntity partialUpdate(UserCreateDto userCreateDto, @MappingTarget UserEntity userEntity);
}