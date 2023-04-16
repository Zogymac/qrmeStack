package ru.quinto.qrme.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quinto.qrme.dto.SkillDto;
import ru.quinto.qrme.entity.Skill;
import ru.quinto.qrme.repository.SkillRepository;
import ru.quinto.qrme.service.SkillService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SkillDto create(SkillDto skillDto) {
        Skill skill = modelMapper.map(skillDto, Skill.class);
        Skill savedSkill = skillRepository.save(skill);
        return modelMapper.map(savedSkill, SkillDto.class);
    }

    @Override
    public SkillDto read(Long id) {
        Optional<Skill> optionalSkill = skillRepository.findById(id);
        return optionalSkill.map(skill -> modelMapper.map(skill, SkillDto.class)).orElse(null);
    }

    @Override
    public SkillDto update(SkillDto skillDto) {
        Skill skill = modelMapper.map(skillDto, Skill.class);
        Skill updatedSkill = skillRepository.save(skill);
        return modelMapper.map(updatedSkill, SkillDto.class);
    }

    @Override
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<SkillDto> findAll() {
        List<Skill> skills = skillRepository.findAll();
        return skills.stream().map(skill -> modelMapper.map(skill, SkillDto.class)).collect(Collectors.toList());
    }
}
