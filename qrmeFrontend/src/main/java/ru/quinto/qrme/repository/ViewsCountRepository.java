package ru.quinto.qrme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.quinto.qrme.entity.ViewsCount;

public interface ViewsCountRepository extends JpaRepository<ViewsCount, Long> {
}
