package ru.quinto.qrme.service;

import ru.quinto.qrme.dto.SkillDto;

import java.util.List;

public interface SkillService {
    SkillDto create(SkillDto skillDto);
    SkillDto read(Long id);
    SkillDto update(SkillDto skillDto);
    void delete(Long id);
    List<SkillDto> findAll();
}
