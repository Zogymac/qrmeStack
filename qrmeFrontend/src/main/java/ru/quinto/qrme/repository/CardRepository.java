package ru.quinto.qrme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.quinto.qrme.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}