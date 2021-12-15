package com.example.atmserver.demo;



import com.example.atmserver.demo.model.Card;
import com.example.atmserver.demo.repository.CardRepository;
import com.example.atmserver.demo.security.entity.User;
import com.example.atmserver.demo.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class AtmserverApplication {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CardRepository cardRepository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User( "user1", "pwd1", "user1@gmail.com",0),
                new User( "user2", "pwd2", "user2@gmail.com",0),
                new User( "user3", "pwd3", "user3@gmail.com",0)
        ).collect(Collectors.toList());
        userRepository.saveAll(users);

        List<Card> cards = Stream.of(
                new Card( "user1" , new BigDecimal(0) ,true ),
                new Card( "user2" ,  new BigDecimal(0) ,true ),
                new Card( "user2" ,  new BigDecimal(0) ,true )
        ).collect(Collectors.toList());
        cardRepository.saveAll(cards);

    }
    public static void main(String[] args) {
        SpringApplication.run(AtmserverApplication.class, args);
    }

}
