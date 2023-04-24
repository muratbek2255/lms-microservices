package com.example.courseservice.dto;


import com.example.courseservice.entity.Course;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParticipantResponse {

    Integer id;

    Course course;
}
