package com.digitalhouse.users_service.services;

import com.digitalhouse.users_service.model.dtos.UserRequest;
import com.digitalhouse.users_service.model.entities.Account;
import com.digitalhouse.users_service.model.entities.User;
import com.digitalhouse.users_service.repositories.AccountRepository;
import com.digitalhouse.users_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;


    //CREATE ACCOUNT
    public void addAccount(User user) {
        var account = Account.builder()
                .userId(user.getId())
                .balance(BigDecimal.ZERO)
                .cvu(createCvu())
                .alias(createAlias())
                .name(user.getFirstname())
                .build();
        accountRepository.save(account);
        log.info("Account added: {}", user);
    }


    public double createCvu(){
        double cvu=0;
        for (int i = 0; i < 22; i++) {
            cvu += Math.floor(Math.random() * 10);
        }
        return cvu;
    }

    public String createAlias(){

        return null;
    }

    /*const generateAlias = (): string => {
  const words = [
        'Cuenta',
                'Personal',
                'Banco',
                'Argentina',
                'Digital',
                'Money',
                'House',
                'Bank',
                'Account',
                'Cartera',
                'Wallet',
                'Pago',
                'Pay',
                'Rapido',
                'Seguro',
  ];
  const length = 3;
        let alias = '';
        for (let i = 0; i < length; i++) {
            alias += words[Math.floor(Math.random() * words.length)];
            if (i < length - 1) {
                alias += '.';
            }
        }
        return alias;
    };*/

}


