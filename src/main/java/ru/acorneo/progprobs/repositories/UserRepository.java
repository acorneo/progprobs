package ru.acorneo.progprobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorneo.progprobs.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
