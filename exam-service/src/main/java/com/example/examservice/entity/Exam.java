package com.example.examservice.entity;


import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "created_at")
    Timestamp created_at;

    @Column(name = "updated_at")
    Timestamp updated_at;

    @Column(name = "password")
    String password;

    @Column(name = "time_limit")
    Time timeLimit;

    @Column(name = "active_from")
    LocalDateTime activeFrom;

    @Column(name = "active_to")
    LocalDateTime activeTo;

    @Column(name = "course_id")
    Integer courseId;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    @Column(name = "question_count")
    Integer questionCount;
}