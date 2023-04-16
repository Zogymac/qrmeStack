package ru.quinto.qrme.service;

import ru.quinto.qrme.dto.EventDto;

import java.util.List;

public interface EventService {
    EventDto create(EventDto eventDto);
    EventDto read(Long id);
    EventDto update(EventDto eventDto);
    void delete(Long id);
    List<EventDto> findAll();
}
