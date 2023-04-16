package ru.quinto.qrme.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quinto.qrme.dto.EventDto;
import ru.quinto.qrme.entity.Event;
import ru.quinto.qrme.repository.EventRepository;
import ru.quinto.qrme.service.EventService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EventDto create(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        Event savedEvent = eventRepository.save(event);
        return modelMapper.map(savedEvent, EventDto.class);
    }

    @Override
    public EventDto read(Long id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        return optionalEvent.map(event -> modelMapper.map(event, EventDto.class)).orElse(null);
    }

    @Override
    public EventDto update(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        Event updatedEvent = eventRepository.save(event);
        return modelMapper.map(updatedEvent, EventDto.class);
    }

    @Override
    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<EventDto> findAll() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> modelMapper.map(event, EventDto.class)).collect(Collectors.toList());
    }
}
