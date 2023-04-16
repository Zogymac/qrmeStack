package ru.quinto.qrme.service;

import org.springframework.stereotype.Service;
import ru.quinto.qrme.dto.CardDto;

import java.util.List;



public interface CardService {
    CardDto create(CardDto cardDto);
    CardDto read(Long id);
    CardDto update(CardDto cardDto);
    void delete(Long id);
    List<CardDto> findAll();
}
