package ru.quinto.qrme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String photoUrl;
    private String fullName;
    private String bio;
    private String qrUrl;
    private LinkDto link;
    private CardDto card;
    private Set<SkillDto> skills = new HashSet<>();
}