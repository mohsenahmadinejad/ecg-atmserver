package com.example.atmserver.demo.controller;

import com.example.atmserver.demo.dto.CardDTO;
import com.example.atmserver.demo.dto.TransactionDetailDTO;
import com.example.atmserver.demo.exception.CardNotFoundException;
import com.example.atmserver.demo.exception.NotEnoughBalanceException;
import com.example.atmserver.demo.model.Card;
import com.example.atmserver.demo.model.TransactionDetail;
import com.example.atmserver.demo.model.TransactionType;
import com.example.atmserver.demo.repository.CardRepository;
import com.example.atmserver.demo.repository.TransactionDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * <h1>TransactionDetail APIs</h1>
 * The TransactionDetailControllerImpl implements some operations on
 * TransactionDetail entity such as add transaction or update
 *
 * @author  Mohsen Ahmadi
 * @version 1.0
 * @since   2021-12-14
 */
@Validated
@RestController
public class TransactionDetailControllerImpl implements TransactionDetailController {

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private TransactionDetailRepository transactionDetailRepository;

  private  Logger LOG= LoggerFactory.getLogger(TransactionDetailControllerImpl.class);

  @Override
  public TransactionDetailDTO addTransaction(TransactionDetailDTO transactionDetailDTO) {
    Card card=cardRepository.findByCardNumber(transactionDetailDTO.getCardNumber());
    if(card==null){
      throw new CardNotFoundException();
    }
    TransactionDetail transactionDetail=TransactionDetailDTO.fromDTO(transactionDetailDTO);
    BigDecimal currentBalance=card.getBalance();
    BigDecimal newBalance=new BigDecimal(0);
    if(transactionDetail.getTransactionType().equals(TransactionType.CASH_DEPOSIT)){
      newBalance=currentBalance.add(transactionDetail.getAmount());
    }else
    if(transactionDetail.getTransactionType().equals(TransactionType.CASH_WITHDRAW)){
      if(currentBalance.compareTo(transactionDetail.getAmount()) < 0 ){
        LOG.error("Balance is not enough");
        throw new NotEnoughBalanceException("Balance is not enough");
      }
      newBalance =currentBalance.subtract(transactionDetail.getAmount());
    }
    card.setBalance(newBalance);
    transactionDetail.setBalance(newBalance);
    transactionDetail.setCard(card);
    cardRepository.save(card);
    LOG.info("Transaction added for card number: "+transactionDetailDTO.getCardNumber());

    return TransactionDetailDTO.toDTO(transactionDetailRepository.save(transactionDetail));
  }


}
