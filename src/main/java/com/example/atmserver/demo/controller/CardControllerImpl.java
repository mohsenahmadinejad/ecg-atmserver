package com.example.atmserver.demo.controller;

import com.example.atmserver.demo.dto.CardDTO;
import com.example.atmserver.demo.exception.CardNotFoundException;
import com.example.atmserver.demo.model.Card;
import com.example.atmserver.demo.repository.CardRepository;
import com.example.atmserver.demo.security.entity.User;
import com.example.atmserver.demo.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
/**
 * <h1>Card APIs</h1>
 * The CardControllerImpl implements some operations on
 * Card entity such as add transaction or getBalance
 *
 * @author  Mohsen Ahmadi
 * @version 1.0
 * @since   2021-12-14
 */
@RestController
@Validated
public class CardControllerImpl implements CardController {

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private UserRepository userRepository;

  private  Logger LOG= LoggerFactory.getLogger(TransactionDetailControllerImpl.class);


  @Override
  public BigDecimal getBalance(String cardNumber) {
    Card card=cardRepository.findByCardNumber(cardNumber);
    if(card==null){
      throw  new CardNotFoundException();
    }
    return card.getBalance();
  }

  @Override
  public CardDTO addCard(CardDTO cardDTO) {
    Card card=CardDTO.fromDTO(cardDTO);
    card.setBalance(new BigDecimal(0));

    User user=new User(cardDTO.getCardNumber(),cardDTO.getPassword(),"", 0);
    userRepository.save(user);
    LOG.info("Card added for card number: "+cardDTO.getCardNumber());

    return CardDTO.toDTO(cardRepository.save(card));
  }


}
