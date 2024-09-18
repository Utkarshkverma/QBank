package com.vermau2k01.cards.service.impl;

import com.vermau2k01.cards.constant.AppConstant;
import com.vermau2k01.cards.dto.CardDto;
import com.vermau2k01.cards.entity.Card;
import com.vermau2k01.cards.exception.CardAlreadyExistsException;
import com.vermau2k01.cards.exception.ResourceNotFoundException;
import com.vermau2k01.cards.mapper.CardMapper;
import com.vermau2k01.cards.repository.CardRepository;
import com.vermau2k01.cards.service.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Random;

@Repository
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {

    private final CardRepository cardRepository;


    @Override
    public void createCard(String mobileNumber) {

        Optional<Card> optionalCards= cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }

        cardRepository.save(createNewCard(mobileNumber));

    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(AppConstant.CREDIT_CARD);
        newCard.setTotalLimit(AppConstant.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(AppConstant.NEW_CARD_LIMIT);
        return newCard;
    }



    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.mapToCardsDto(cards, new CardDto());

    }

    @Override
    public boolean updateCard(CardDto cardsDto) {
        Card cards = cardRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardMapper.mapToCards(cardsDto, cards);
        cardRepository.save(cards);
        return  true;

    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card cards = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardRepository.deleteById(cards.getCardId());
        return true;
    }
}