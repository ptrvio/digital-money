package com.digitalhouse.users_service.services;


import com.digitalhouse.users_service.model.entities.Account;
import com.digitalhouse.users_service.model.entities.User;
import com.digitalhouse.users_service.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;


    //CREATE ACCOUNT
    public void createAccount(User user) {
        var account = Account.builder()
                .user(user)
                .balance(BigDecimal.ZERO)
                .cvu(generateCvu())
                .alias(generateAlias())
                .name(user.getFirstname())
                .build();

        accountRepository.save(account);
        log.info("Account created: {}", account);
    }


    public static String generateCvu() {
        SecureRandom random = new SecureRandom();
        StringBuilder cvu = new StringBuilder(22);
        for (int i = 0; i < 22; i++) {
            cvu.append(random.nextInt(10)); // Genera un número entre 0 y 9
        }
        return cvu.toString();
    }

    public List<String> getWordsList() {

        try {
            Path filePath = new ClassPathResource("files/palabras.txt").getFile().toPath();
            String fileContent = Files.readString(filePath);
            List<String> wordsList = Arrays.asList(fileContent.split("\\s*,\\s*"));

            return wordsList;
        }
        catch (IOException e) {
            log.error("Error al leer el archivo: " + e.getMessage());
            throw new RuntimeException("Error getting the file palabras.txt",e);
        }
    }

    private String generateAlias() {
        int length = 3;
        String alias = "";
        try{
            List<String> words = getWordsList();
            for (int i = 0; i < length; i++) {
                Double index = (Math.floor(Math.random() * words.size()));
                alias += words.get(index.intValue());
                if (i < length - 1) {
                    alias += '.';
                }
            }
            log.info("Alias generado: " + alias);
            return alias;
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }
}



