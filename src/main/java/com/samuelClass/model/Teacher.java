package com.samuelClass.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(
        name = "teacher",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "email",
                name = "email"
        )
)
public class Teacher {

    @Id
    @SequenceGenerator(
            sequenceName = "teacherId_generator",
            name = "teacherId_generator",
            initialValue = 100,
            allocationSize = 2
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacherId_generator"
    )
    @Column(name = "userId", nullable = false)
    private Long id;

    @Column(name = "fName", nullable = false)
    private String firstName;

    @Column(name = "lName")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "assigned_Class")
    private Level level;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "INVALID EMAIL FORMAT")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnoreProperties("teacher")
    @ToString.Exclude
    private List<Student> student;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Teacher teacher = (Teacher) o;
        return getId() != null && Objects.equals(getId(), teacher.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public Teacher(String firstName, String lastName,String email, Role role, Level level, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.level = level;

        this.password = password;
    }
}
