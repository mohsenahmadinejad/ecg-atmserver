package com.example.atmserver.demo.controller;


import com.example.atmserver.demo.dto.CardDTO;
import com.example.atmserver.demo.model.Card;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

/**
 * Please think about other possible operations.
 */
@Api(value = "CardControllerAPI",produces = MediaType.APPLICATION_JSON_VALUE)
public interface CardController {

//  @GetMapping("/board/{id}")
//  CardDTO getBoard(@PathVariable("id") long id);
//
//  @GetMapping("/board/{id}/isvalid")
//  boolean validateBoard(@PathVariable("id") long id);

  @ApiOperation("Get card balance")
  @ApiResponses(value = {@ApiResponse(code=200,message = "OK",response = BigDecimal.class)})
  @GetMapping("/getBalance/{cardNumber}")
  BigDecimal getBalance(@ApiParam(value = "Card number to get balance", required = true) @PathVariable("cardNumber") String cardNumber);

  @ApiOperation("Add new card")
  @ApiResponses(value = {@ApiResponse(code=200,message = "OK",response = CardDTO.class)})
  @PostMapping("/card")
  CardDTO addCard(@ApiParam(value = "", required = true) @RequestBody CardDTO cardDTO);
}
