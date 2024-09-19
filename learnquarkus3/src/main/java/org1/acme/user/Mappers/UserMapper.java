package org1.acme.user.Mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org1.acme.user.domain.UserModel;
import org1.acme.user.DTO.UserTO;
import org.mapstruct.Mapper;

@ApplicationScoped
@Mapper(componentModel = "cdi",unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    UserTO map(UserModel user);

    @Mapping(target = "id",ignore = true)
    UserModel map(UserTO user);

}
