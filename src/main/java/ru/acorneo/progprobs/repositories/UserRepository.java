package ru.acorneo.progprobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorneo.progprobs.data.model.User;

import java.util.List;

// Integer as a second value means primary key type
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsername(String username);
}
