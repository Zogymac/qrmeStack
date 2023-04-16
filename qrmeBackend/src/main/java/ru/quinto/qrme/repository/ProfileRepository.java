package ru.quinto.qrme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.quinto.qrme.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}