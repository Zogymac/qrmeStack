package ru.quinto.qrme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDto {
    private Long id;
    private String name;
    private String description;
    private String logoUrl;
    private ProfileDto profile;
}
