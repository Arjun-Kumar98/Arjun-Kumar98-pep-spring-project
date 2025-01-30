package com.example.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import java.util.Optional;
import java.util.List;

import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public Message createMessage(Message message){
        String msg = message.getMessageText();
        if(!msg.isBlank() && msg.length()<=255){
            return messageRepository.save(message);
        }
        return null;
    }

    public List<Message> retrieveAllMessages(){
        return messageRepository.findAll();
    }

    public Message retrieveMessageById(int messageId){
        Optional<Message> optMessage = messageRepository.findById(messageId);
        if(optMessage.isPresent()){
            return optMessage.get();
        }
        return null;
    }

    public void deleteMessageById(int messageId){
         messageRepository.deleteById(messageId);
    }
    public Message updateMessageById(int messageId,String messageTxt){
           if(!messageTxt.isBlank()&&messageTxt.length()<=255){
           Optional<Message> optMessage = messageRepository.findById(messageId);
           if(optMessage.isPresent()){
              Message msg = optMessage.get();
              msg.setMessageText(messageTxt);
              return messageRepository.save(msg);
           }
           return null;
        }
           return null;
    }

    public List<Message> retrieveAllMessagesByUserId(int userId){
        return messageRepository.findByPostedBy(userId);
    }
}
