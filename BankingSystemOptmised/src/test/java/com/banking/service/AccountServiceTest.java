package com.banking.service;

import com.banking.entities.Account;
import com.banking.entities.Customer;
import com.banking.repo.AccountRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepo accountRepo;

    @Mock
    private TransactionService transactionService;

    @Mock
    private Account account;

    @Mock
    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void testViewAccount() {
        when(account.getBalance()).thenReturn(1000.0);
        when(accountRepo.findById(1)).thenReturn(Optional.of(account));
        Account account1 = accountService.viewAccount(1);
        assertThat(account1).isNotNull();
        assertThat(account1.getBalance()).isEqualTo(1000.0);
    }

    @Test
    public void testGetAllAccount() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(account);
        when(account.getBalance()).thenReturn(10001.0);
        when(accountRepo.findAll()).thenReturn(accountList);
        List<Account> resultList = accountService.getAllAccount();
        assertThat(resultList).isNotNull();
        assertThat(resultList.size()).isEqualTo(1);
        assertThat(resultList.get(0).getBalance()).isEqualTo(10001.0);
    }


}
