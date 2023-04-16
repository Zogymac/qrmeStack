package ru.quinto.qrme.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.quinto.qrme.dto.EventDto;
import ru.quinto.qrme.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("")
    public List<EventDto> getEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable Long id) {
        return eventService.read(id);
    }

    @PostMapping("")
    public EventDto createEvent(@RequestBody EventDto eventDto) {
        return eventService.create(eventDto);
    }

    @PutMapping("/{id}")
    public EventDto updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        return eventService.update(eventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }
}
