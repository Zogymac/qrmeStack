package ru.quinto.qrme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.quinto.qrme.dto.LinkDto;
import ru.quinto.qrme.service.LinkService;

import java.util.List;

@RestController
@RequestMapping("/api/links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("")
    public List<LinkDto> getLinks() {
        return linkService.findAll();
    }

    @GetMapping("/{id}")
    public LinkDto getLinkById(@PathVariable Long id) {
        return linkService.read(id);
    }

    @PostMapping("")
    public LinkDto createLink(@RequestBody LinkDto linkDto) {
        return linkService.create(linkDto);
    }

    @PutMapping("/{id}")
    public LinkDto updateLink(@PathVariable Long id, @RequestBody LinkDto linkDto) {
        return linkService.update(linkDto);
    }

    @DeleteMapping("/{id}")
    public void deleteLink(@PathVariable Long id) {
        linkService.delete(id);
    }
}
