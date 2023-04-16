package ru.quinto.qrme.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;

@Entity
@Table(name = "links")
@Data
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link_url", length = 255)
    private String linkUrl;

    @Column(name = "image_url", length = 255)
    private String imageUrl;
}
