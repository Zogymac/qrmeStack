package ru.quinto.qrme.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.quinto.qrme.entity.CardType;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {
    private Long id;
    private CardType type;
    private String name;
    private String description;
    private String logoUrl;
    private String productUrl;
    private String portfolioUrl;

    @JsonProperty("type") // для корректного маппинга в json
    public String getCardType() {
        return type.toString();
    }
}
