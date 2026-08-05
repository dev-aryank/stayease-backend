package dev.aryank.stayease.dto;

import dev.aryank.stayease.entity.User;
import dev.aryank.stayease.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
