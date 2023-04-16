package ru.quinto.qrme.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quinto.qrme.dto.ProfileDto;
import ru.quinto.qrme.entity.Profile;
import ru.quinto.qrme.repository.ProfileRepository;
import ru.quinto.qrme.service.ProfileService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProfileDto create(ProfileDto profileDto) {
        Profile profile = modelMapper.map(profileDto, Profile.class);
        Profile savedProfile = profileRepository.save(profile);
        return modelMapper.map(savedProfile, ProfileDto.class);
    }

    @Override
    public ProfileDto read(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);
        return optionalProfile.map(profile -> modelMapper.map(profile, ProfileDto.class)).orElse(null);
    }

    @Override
    public ProfileDto update(ProfileDto profileDto) {
        Profile profile = modelMapper.map(profileDto, Profile.class);
        Profile updatedProfile = profileRepository.save(profile);
        return modelMapper.map(updatedProfile, ProfileDto.class);
    }

    @Override
    public void delete(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<ProfileDto> findAll() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(profile -> modelMapper.map(profile, ProfileDto.class)).collect(Collectors.toList());
    }
}
