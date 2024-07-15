package com.quantweb.springserver.user.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import solitour_backend.solitour.user.dto.UserPostingResponse;
import solitour_backend.solitour.user.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

  UserPostingResponse mapToUserPostingResponse(User user);
}
