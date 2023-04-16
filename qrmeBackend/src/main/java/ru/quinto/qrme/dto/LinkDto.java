package ru.quinto.qrme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LinkDto {
    private Long id;
    private String linkUrl;
    private String imageUrl;
}
