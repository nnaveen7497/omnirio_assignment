package com.omnirio.accountservice.service;

import com.omnirio.accountservice.model.Account;
import com.omnirio.accountservice.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private List<Account> accountList;

    private final WebClient.Builder webClientBuilder;

    AccountService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public List<Account> getAccountList() {
        List<Account> accountDtoList = accountList.stream().map(account -> {
            User user = webClientBuilder.build()
                    .get()
                    .uri("http://customer-service/users/" + account.getCustomerId())
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
            account.setUser(user);
            return account;
        }).collect(Collectors.toList());
        return accountDtoList;
    }

    public Account saveAccountDetails(Account account) {
        User user = webClientBuilder.build()
                .get()
                .uri("http://customer-service/users/" + account.getCustomerId())
                .retrieve()
                .bodyToMono(User.class)
                .block();
        if (user == null) {
            user = new User();
            user.setUserId(account.getAccountId());
            user.setUserName(account.getCustomerName());
            user.setRoleCode("ROLE_CUSTOMER");
            user.setGender("M");
            user = webClientBuilder.build()
                    .post()
                    .uri("http://customer-service/users")
                    .body(Mono.just(user), User.class)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();
        }
        account.setCustomerId(user.getUserId());
        account.setCustomerName(user.getUserName());
        account.setMinorIndicator(checkFromMinor(user));
        account.setUser(user);
        accountList.add(account);
        return account;
    }

    private String checkFromMinor(User user) {
        String minor = "N";
        if (user.getDateOfBirth() != null) {
            int age = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
            if (age < 18) {
                minor = "Y";
            }
        }
        return minor;
    }
}
