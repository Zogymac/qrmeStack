package ru.quinto.qrme.service;

import ru.quinto.qrme.dto.ProfileDto;

import java.util.List;

public interface ProfileService {
    ProfileDto create(ProfileDto profileDto);
    ProfileDto read(Long id);
    ProfileDto update(ProfileDto profileDto);
    void delete(Long id);
    List<ProfileDto> findAll();
}
