package com.example.controller;
import com.example.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @Controller

public class SocialMediaController {

    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;
    


    @PostMapping("/register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account){
        try{
        Optional<Account> optAccount = accountService.retrieveUserByUsername(account.getUsername());
        if(optAccount==null){
            Account acct = accountService.registerUser(account);
            return ResponseEntity.ok(acct);
        }else{
            return ResponseEntity.status(409).body(null);
        }
    }catch(RuntimeException e){
        return ResponseEntity.status(400).body(null);
    }


    }
    


}
