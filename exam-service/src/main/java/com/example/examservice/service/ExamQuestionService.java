package com.example.examservice.service;


import com.example.examservice.dto.StudentAnswerRequest;
import com.example.examservice.entity.Choice;
import com.example.examservice.entity.ExamQuestion;
import com.example.examservice.entity.Score;
import com.example.examservice.entity.StudentAnswer;
import com.example.examservice.repository.ExamQuestionRepository;
import com.example.examservice.repository.ScoreRepository;
import com.example.examservice.repository.StudentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExamQuestionService {

    private final StudentAnswerRepository studentAnswerRepository;

    private final ExamQuestionRepository examQuestionRepository;

    private final ScoreRepository scoreRepository;

    @Autowired
    public ExamQuestionService(StudentAnswerRepository studentAnswerRepository, ExamQuestionRepository examQuestionRepository, ScoreRepository scoreRepository) {
        this.studentAnswerRepository = studentAnswerRepository;
        this.examQuestionRepository = examQuestionRepository;
        this.scoreRepository = scoreRepository;
    }

    public String takeExam(String password, StudentAnswerRequest studentAnswerRequest) {

        List<ExamQuestion> examQuestions = examQuestionRepository.findByExamPassword(password);

        examQuestions.stream().forEach(
                examQuestion -> {
                    StudentAnswer studentAnswer = new StudentAnswer();

                    studentAnswer.setExam(examQuestion);
                    studentAnswer.setAnswer(studentAnswerRequest.getAnswer());

                    studentAnswerRepository.save(studentAnswer);

                    Score score = new Score();

                    Choice choice = new Choice();

                    if (studentAnswer.getExam().getQuestion().getQuestionText().equals(choice.getChoiceText())) {

                        score.setScore(score.getScore() + 1);
                    }
                    score.setExam(examQuestion.getExam());

                    scoreRepository.save(score);
                }
        );

        return "Take Exam";
    }
}
