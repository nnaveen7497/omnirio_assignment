package com.omnirio.accountservice.service;

import com.omnirio.accountservice.model.Account;
import com.omnirio.accountservice.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private AccountService accountService;

    @Test
    @DisplayName("Test to get all the list of accounts")
    public void getAccountListTest() {
        Account account = new Account();
        account.setAccountId("53565");
        account.setAccountType("Savings");
        account.setBranch("Pune");
        account.setCustomerId("abc");
        account.setCustomerName("Nishant");
        account.setOpenDate(LocalDate.now());
        account.setMinorIndicator("N");
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);

        Account accountDto = new Account();
        accountDto.setAccountId("53565");
        accountDto.setAccountType("Savings");
        accountDto.setBranch("Pune");
        accountDto.setCustomerId("abc");
        accountDto.setCustomerName("Nishant");
        accountDto.setOpenDate(LocalDate.now());
        accountDto.setMinorIndicator("N");

        User userDto = new User();
        userDto.setUserId("abc");
        userDto.setUserName("Nishant");
        userDto.setGender("M");
        userDto.setRoleCode("ROLE_CUSTOMER");
        userDto.setPhoneNumber("9762356464");
        when(webClientBuilder.build().get().uri(any(String.class)).retrieve().bodyToMono(User.class).block()).thenReturn(userDto);

        List<Account> accountDtoList = accountService.getAccountList();
        assertAll("Assert the results", () -> {
            assertNotNull(accountDtoList, "Result is empty");
            assertEquals(1, accountDtoList.size(), "Size is not equal");
            assertEquals(accountDto.getAccountId(), accountDtoList.get(0).getAccountId(), "Account Id not equal");
        });
    }
}
