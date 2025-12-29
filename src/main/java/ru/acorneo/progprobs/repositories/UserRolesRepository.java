package ru.acorneo.progprobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.acorneo.progprobs.data.model.UserRoles;

// Integer as a second value means primary key type
public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {
}
