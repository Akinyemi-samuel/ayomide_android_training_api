package com.samuelClass.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "admin",
        uniqueConstraints = @UniqueConstraint(
                name = "email",
                columnNames = "email"
        )
)
public class Admin {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_generator"
    )
    @SequenceGenerator(
            name = "admin_generator",
            sequenceName = "admin_generator"
    )
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Email(message = "INVALID EMAIL FORMAT")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;
}
