package ru.quinto.qrme.service;

import ru.quinto.qrme.dto.LinkDto;

import java.util.List;

public interface LinkService {
    LinkDto create(LinkDto linkDto);
    LinkDto read(Long id);
    LinkDto update(LinkDto linkDto);
    void delete(Long id);
    List<LinkDto> findAll();
}
