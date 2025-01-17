package org.example.controller;

import org.example.BaseIntegrationTest;
import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    public void setUp() {

        // Create a sample account
        account = new Account();
        account.setUsername("testuser");
        account.setBiography("Test biography");
        account.setBirthday(LocalDate.of(2000, 1, 1));
    }

    @Test
    public void testCreateAccount() throws Exception {
        MvcResult result = mockMvc.perform(post("/account/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":0,\"username\":\"string\",\"biography\":\"string\",\"birthday\":\"2025-01-17\"}"))
                .andReturn(); // Capture the result

        // Now assert the JSON path
        mockMvc.perform(asyncDispatch(result))
                .andExpect(jsonPath("$.username", is("string")))
                .andExpect(jsonPath("$.biography", is("string")))
                .andExpect(jsonPath("$.birthday", is("2025-01-17")));
    }



    @Test
    public void testGetAllAccounts() throws Exception {
        accountRepository.deleteAll();
        accountRepository.save(account);
        MvcResult result = mockMvc.perform(get("/account/getAll"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].username", is("testuser")))
                .andExpect(jsonPath("$[0].biography", is("Test biography")))
                .andExpect(jsonPath("$[0].birthday", is("2000-01-01")));
    }
}
