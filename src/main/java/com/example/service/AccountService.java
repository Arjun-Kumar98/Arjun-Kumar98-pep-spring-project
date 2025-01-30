package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import com.example.entity.Account;
import com.example.repository.AccountRepository;;

public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account registerUser(Account account){
       if((!account.getUsername().isBlank())&&(account.getPassword().length()>=4)){
        Optional<Account> optUser = accountRepository.findByUsername(account.getUsername());
       if(!optUser.isPresent()){
        return accountRepository.save(account);
        
       }else{
        return null;
       }
       }

        return null;
    }

    public Account loginUser(Account account){
        Optional<Account> loginUser = accountRepository.findByUsernameAndPassword(account.getUsername(),account.getPassword());
        if(loginUser.isPresent()){
            return loginUser.get();
        }
        return null;
    }
}
