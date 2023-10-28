package com.samuelClass.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
public class Courses {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courses_generator"
    )
    @SequenceGenerator(
            name = "courses_generator",
            sequenceName = "courses_generator",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "course_Name")
    private String courseName;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

}
