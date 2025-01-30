package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Message;
import java.util.Optional;
public interface MessageRepository extends JpaRepository<Message,Integer> {
}
