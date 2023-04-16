package ru.quinto.qrme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.quinto.qrme.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}