package dev.aryank.stayease.dto;

import dev.aryank.stayease.entity.User;
import dev.aryank.stayease.entity.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GuestDto {
    private Long id;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
}
