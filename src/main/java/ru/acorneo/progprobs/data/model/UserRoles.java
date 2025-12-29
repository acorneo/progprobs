package ru.acorneo.progprobs.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="roles")
@Data
public class UserRoles {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    private String role;
}
