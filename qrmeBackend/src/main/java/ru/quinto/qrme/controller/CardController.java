package ru.quinto.qrme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.quinto.qrme.dto.CardDto;
import ru.quinto.qrme.entity.Card;
import ru.quinto.qrme.service.CardService;
import ru.quinto.qrme.service.impl.CardServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/cards/")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable Long id) {
        CardDto cardDto = cardService.read(id);
        return ResponseEntity.ok(cardDto);
    }

    @PostMapping
    public ResponseEntity<CardDto> createCard(@RequestBody CardDto cardDto) {
        CardDto createdCardDto = cardService.create(cardDto);
        return ResponseEntity.ok(createdCardDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardDto> updateCard(@PathVariable Long id, @RequestBody CardDto cardDto) {
        //cardDto.setId(id);
        CardDto updatedCardDto = cardService.update(cardDto);
        return ResponseEntity.ok(updatedCardDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.delete(id);
        return ResponseEntity.ok().build();
    }

}
