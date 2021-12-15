package com.example.atmserver.demo;


import com.example.atmserver.demo.dto.CardDTO;
import com.example.atmserver.demo.dto.TransactionDetailDTO;
import com.example.atmserver.demo.model.TransactionType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.Before;

@SpringBootTest
@AutoConfigureMockMvc
class AtmIntegrationTest {
    private static  CardDTO cardDTO1=new CardDTO();
    private static  CardDTO cardDTO2=new CardDTO();
    private static TransactionDetailDTO transactionDetailDTO11;
    private static TransactionDetailDTO transactionDetailDTO12;
    private static TransactionDetailDTO transactionDetailDTO13;
    private static TransactionDetailDTO transactionDetailDTO14;
    private static TransactionDetailDTO transactionDetailDTO21;
    private static TransactionDetailDTO transactionDetailDTO_badRequest;
    @Autowired
    private MockMvc mockMvc;

    private Logger LOG = LoggerFactory.getLogger(AtmIntegrationTest.class);

    @Before
    public void initCard() {
        cardDTO1.setCardNumber("1");
        cardDTO1.setBalance(new BigDecimal(0));
        cardDTO1.setEnabled(true);
        cardDTO1.setPassword("123");
        cardDTO1.setFailedAttempt(0);

        cardDTO2.setCardNumber("2");
        cardDTO2.setBalance(new BigDecimal(0));
        cardDTO2.setEnabled(true);
        cardDTO2.setPassword("1234");
        cardDTO2.setFailedAttempt(0);
    }
    public void initTransaction(){
        transactionDetailDTO11=new TransactionDetailDTO(new BigDecimal(100), TransactionType.CASH_DEPOSIT,"1",new BigDecimal(0));
        transactionDetailDTO12=new TransactionDetailDTO(new BigDecimal(800), TransactionType.CASH_DEPOSIT,"1",new BigDecimal(0));
        transactionDetailDTO13=new TransactionDetailDTO(new BigDecimal(50),  TransactionType.CASH_WITHDRAW,"1",new BigDecimal(0));
        transactionDetailDTO14=new TransactionDetailDTO(new BigDecimal(200), TransactionType.CASH_WITHDRAW,"1",new BigDecimal(0));
        transactionDetailDTO21=new TransactionDetailDTO(new BigDecimal(100), TransactionType.CASH_DEPOSIT,"2",new BigDecimal(0));
        transactionDetailDTO_badRequest=new TransactionDetailDTO(new BigDecimal(200), TransactionType.CASH_DEPOSIT,"33",new BigDecimal(0));

    }

    @Test
    void addCard() throws Exception {
        initCard();
        String  jsonBody = new ObjectMapper().writeValueAsString(cardDTO1);
        this.mockMvc.perform(post("/card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("1"));

        jsonBody = new ObjectMapper().writeValueAsString(cardDTO2);
        this.mockMvc.perform(post("/card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("2"));

    }

    @Test
    void addTransaction() throws Exception {
        initTransaction();
        addCard();
        String  jsonBody = new ObjectMapper().writeValueAsString(transactionDetailDTO11);
        this.mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("100.0"));

        jsonBody = jsonBody = new ObjectMapper().writeValueAsString(transactionDetailDTO14);
        this.mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isNotAcceptable());

        jsonBody = new ObjectMapper().writeValueAsString(transactionDetailDTO12);
        this.mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("900.0"));

        jsonBody = jsonBody = new ObjectMapper().writeValueAsString(transactionDetailDTO14);
        this.mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("700.0"));

        jsonBody = jsonBody = new ObjectMapper().writeValueAsString(transactionDetailDTO_badRequest);
        this.mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

//
//
//
    @Test
    void retrievingAJustUploadedBoard() throws Exception {
        addTransaction();
        this.mockMvc.perform(get("/getBalance/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("700.00"));
    }


}