package ru.quinto.qrme.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.quinto.qrme.dto.CardDto;
import ru.quinto.qrme.entity.Card;
import ru.quinto.qrme.repository.CardRepository;
import ru.quinto.qrme.service.CardService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CardDto create(CardDto cardDto) {
        Card card = modelMapper.map(cardDto, Card.class);
        Card savedCard = cardRepository.save(card);
        return modelMapper.map(savedCard, CardDto.class);
    }

    @Override
    public CardDto read(Long id) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        return optionalCard.map(card -> modelMapper.map(card, CardDto.class)).orElse(null);
    }

    @Override
    public CardDto update(CardDto cardDto) {
        Card card = modelMapper.map(cardDto, Card.class);
        Card updatedCard = cardRepository.save(card);
        return modelMapper.map(updatedCard, CardDto.class);
    }

    @Override
    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

    @Override
    public List<CardDto> findAll() {
        List<Card> cards = cardRepository.findAll();
        return cards.stream().map(card -> modelMapper.map(card, CardDto.class)).collect(Collectors.toList());
    }
}

