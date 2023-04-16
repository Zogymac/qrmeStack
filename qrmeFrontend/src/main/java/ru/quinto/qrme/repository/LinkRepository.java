package ru.quinto.qrme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.quinto.qrme.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
}