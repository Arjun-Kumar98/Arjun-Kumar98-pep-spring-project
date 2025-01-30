package com.example.controller;
import com.example.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

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

    @PostMapping("/login")
    public ResponseEntity<Account> loginUser(@RequestBody Account account){
        try{
            Account acct = accountService.loginUser(account);
            if(acct!=null){
                return ResponseEntity.ok(acct);
            }
            return ResponseEntity.status(401).body(null);
        }catch(RuntimeException e){
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        try{
        Optional<Account> acct = accountService.retrieveUserByUserId(message.getPostedBy());
        if(acct!=null){ 
        Message msg = messageService.createMessage(message);
       
        if(msg!=null){
            return ResponseEntity.ok(msg);
        }else{
            return ResponseEntity.status(400).body(null);
        }
    }else{
        return ResponseEntity.status(400).body(null);
    }
}catch(RuntimeException e){
    return ResponseEntity.status(400).body(null);
}
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> retrieveAllMessages(){
        List<Message> msgList = messageService.retrieveAllMessages();
        return ResponseEntity.ok(msgList);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> retrieveMessageById(@PathVariable("messageId") int msgId){
              Message msg = messageService.retrieveMessageById(msgId);
              return ResponseEntity.ok(msg);
    }

   @DeleteMapping("/messages/{messageId}")
   public ResponseEntity<Integer> deleteMessageById(@PathVariable("messageId") int msgId){
                 Message msg = messageService.retrieveMessageById(msgId);
                 if(msg!=null){
                    messageService.deleteMessageById(msgId);
                    return ResponseEntity.ok().body(1);
                 }else{
                    return ResponseEntity.ok().body(null);
                 }
   }

   @PatchMapping("/messages/{messageId}")
   public ResponseEntity<Integer> updateMessageById(@RequestBody Message msg,@PathVariable("messageId") int msgId){
    try{
    Message message = messageService.retrieveMessageById(msgId);
    if(message!=null){
      Message messages = messageService.updateMessageById(msgId,msg.getMessageText());
      if(messages!=null){
        return ResponseEntity.status(200).body(1);
      }else{
        return ResponseEntity.status(400).body(null);
      }
    }
    return ResponseEntity.status(400).body(null);
}catch(RuntimeException e){
    return ResponseEntity.status(400).body(null);
}
  
   }

   @GetMapping("/accounts/{accountId}/messages")
   public ResponseEntity<List<Message>> retrieveAllMessagesByUserId(@PathVariable("accountId") int acctId){
    List<Message> msgList = messageService.retrieveAllMessagesByUserId(acctId);
    return ResponseEntity.ok(msgList);
   }

    }
    



