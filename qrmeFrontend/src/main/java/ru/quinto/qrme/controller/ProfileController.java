package ru.quinto.qrme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.quinto.qrme.dto.ProfileDto;
import ru.quinto.qrme.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("")
    public List<ProfileDto> getProfiles() {
        return profileService.findAll();
    }

    @GetMapping("/{id}")
    public ProfileDto getProfileById(@PathVariable Long id) {
        return profileService.read(id);
    }

    @PostMapping("")
    public ProfileDto createProfile(@RequestBody ProfileDto profileDto) {
        return profileService.create(profileDto);
    }

    @PutMapping("/{id}")
    public ProfileDto updateProfile(@PathVariable Long id, @RequestBody ProfileDto profileDto) {
        return profileService.update(profileDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileService.delete(id);
    }
}
