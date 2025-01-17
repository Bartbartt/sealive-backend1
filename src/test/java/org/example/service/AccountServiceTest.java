package org.example.service;

import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void testCreateAccount() throws Exception {
        // Prepare the Account object
        Account account = new Account();
        account.setUsername("testuser");
        account.setBiography("Test biography");
        account.setBirthday(LocalDate.of(2000, 1, 1));

        // Mock the repository save method
        when(accountRepository.save(account)).thenReturn(account);

        // Call the create method from accountService
        CompletableFuture<Account> futureAccount = accountService.create(account);

        // Assertions
        assertEquals("testuser", futureAccount.get().getUsername());
        assertEquals("Test biography", futureAccount.get().getBiography());
        assertEquals(LocalDate.of(2000, 1, 1), futureAccount.get().getBirthday());
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        // Create multiple Account objects
        Account account1 = new Account();
        account1.setUsername("user1");
        account1.setBiography("Biography 1");
        account1.setBirthday(LocalDate.of(1990, 5, 15));

        Account account2 = new Account();
        account2.setUsername("user2");
        account2.setBiography("Biography 2");
        account2.setBirthday(LocalDate.of(1995, 8, 20));

        List<Account> accounts = Arrays.asList(account1, account2);

        // Mock the repository findAll method
        when(accountRepository.findAll()).thenReturn(accounts);

        // Call the getAll method from accountService
        CompletableFuture<List<Account>> futureAccounts = accountService.getAll();

        // Assertions
        assertEquals(2, futureAccounts.get().size());
        assertEquals("user1", futureAccounts.get().get(0).getUsername());
        assertEquals("user2", futureAccounts.get().get(1).getUsername());
    }
}