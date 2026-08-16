package dev.aryank.stayease.service;

import dev.aryank.stayease.dto.ProfileUpdateRequestDto;
import dev.aryank.stayease.dto.UserDto;
import dev.aryank.stayease.entity.User;

public interface UserService {

    User getUserById(Long id);


    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
