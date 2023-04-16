package ru.quinto.qrme.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quinto.qrme.dto.LinkDto;
import ru.quinto.qrme.entity.Link;
import ru.quinto.qrme.repository.LinkRepository;
import ru.quinto.qrme.service.LinkService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LinkDto create(LinkDto linkDto) {
        Link link = modelMapper.map(linkDto, Link.class);
        Link savedLink = linkRepository.save(link);
        return modelMapper.map(savedLink, LinkDto.class);
    }

    @Override
    public LinkDto read(Long id) {
        Optional<Link> optionalLink = linkRepository.findById(id);
        return optionalLink.map(link -> modelMapper.map(link, LinkDto.class)).orElse(null);
    }

    @Override
    public LinkDto update(LinkDto linkDto) {
        Link link = modelMapper.map(linkDto, Link.class);
        Link updatedLink = linkRepository.save(link);
        return modelMapper.map(updatedLink, LinkDto.class);
    }

    @Override
    public void delete(Long id) {
        linkRepository.deleteById(id);
    }

    @Override
    public List<LinkDto> findAll() {
        List<Link> links = linkRepository.findAll();
        return links.stream().map(link -> modelMapper.map(link, LinkDto.class)).collect(Collectors.toList());
    }
}
