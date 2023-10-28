package com.samuelClass.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_generator"
    )
    @SequenceGenerator(
            name = "student_generator",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "fName", nullable = false)
    private String firstName;

    @Column(name = "lName", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Level level;

    @ManyToOne()
    @JoinColumn(name = "teacher_id")
    @JsonIgnoreProperties("student")
    private Teacher teacher;
}
