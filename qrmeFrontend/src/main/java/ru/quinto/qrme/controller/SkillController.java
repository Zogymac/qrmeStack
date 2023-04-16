package ru.quinto.qrme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import ru.quinto.qrme.dto.SkillDto;
import ru.quinto.qrme.service.SkillService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/{id}")
    public ResponseEntity<SkillDto> getSkillById(@PathVariable Long id) {
        SkillDto skillDto = skillService.read(id);
        return ResponseEntity.ok(skillDto);
    }

    @PostMapping
    public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
        SkillDto createdSkillDto = skillService.create(skillDto);
        return ResponseEntity.created(URI.create("/api/skills/" + createdSkillDto.getId()))
                .body(createdSkillDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillDto> updateSkill(@PathVariable Long id, @RequestBody SkillDto skillDto) {
        SkillDto updatedSkillDto = skillService.update(skillDto);
        return ResponseEntity.ok(updatedSkillDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SkillDto>> getAllSkills() {
        List<SkillDto> skills = skillService.findAll();
        return ResponseEntity.ok(skills);
    }
}

