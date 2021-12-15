package com.example.atmserver.demo.controller;


import com.example.atmserver.demo.dto.CardDTO;
import com.example.atmserver.demo.dto.TransactionDetailDTO;
import com.example.atmserver.demo.model.Card;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Please think about other possible operations.
 */

public interface TransactionDetailController {

  @ApiOperation("Add new transaction")
  @ApiResponses(value = {@ApiResponse(code=200,message = "OK",response = TransactionDetailDTO.class)})
  @PostMapping("/transaction")
  TransactionDetailDTO addTransaction(@ApiParam(value = "", required = true) @RequestBody TransactionDetailDTO transactionDetailDTO);
}
