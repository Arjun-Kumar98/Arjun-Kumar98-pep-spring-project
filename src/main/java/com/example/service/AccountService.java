package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Optional<Account> retrieveUserByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Optional<Account> retrieveUserByUserId(int userId) {
        return accountRepository.findById(userId);
    }

    public Account registerUser(Account account) {
        if ((!account.getUsername().isBlank()) && (account.getPassword().length() >= 4)) {
            return accountRepository.save(account);
        }
        return null;
    }

    public Account loginUser(Account account) {
        Optional<Account> loginUser = accountRepository.findByUsernameAndPassword(account.getUsername(),
                account.getPassword());
        if (loginUser.isPresent()) {
            return loginUser.get();
        }
        return null;
    }
}
