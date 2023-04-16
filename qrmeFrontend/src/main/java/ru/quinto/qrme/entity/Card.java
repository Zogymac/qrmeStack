package ru.quinto.qrme.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;


@Data
@Entity
@Table(name = "cards")
public class Card {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CardType type;

    @Column(length = 50)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "product_url")
    private String productUrl;

    @Column(name = "portfolio_url")
    private String portfolioUrl;

    @Override
    public String toString() {
        return name != null ? name : "";
    }

}

