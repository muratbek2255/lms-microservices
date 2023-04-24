package com.example.examservice.dto;


import com.example.examservice.entity.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Time;
import java.time.LocalDateTime;


@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExamRequest {

    String name;

    String password;

    Time timeLimit;

    LocalDateTime activeFrom;

    LocalDateTime activeTo;

    CategoryRequest categoryRequest;

    Integer questionCount;
}
