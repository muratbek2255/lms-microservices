package com.example.paymentservice;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    @Column(name = "created_at")
    Timestamp created_at;

    @Column(name = "updated_at")
    Timestamp updated_at;

    @Column(name = "sum_of_course")
    Integer sumOfFavour;

    @Column(name = "account_check")
    String accountCheck;

    @Column(name = "is_checked")
    Boolean isChecked;

    @Column(name = "is_finished")
    Boolean finished;

    @Column(name = "course_id")
    Integer courseId;
}
