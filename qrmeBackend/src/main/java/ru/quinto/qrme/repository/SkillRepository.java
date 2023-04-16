package ru.quinto.qrme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.quinto.qrme.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}