package com.example.examservice.service;


import com.example.examservice.dto.StudentAnswerRequest;
import com.example.examservice.entity.ExamQuestion;
import com.example.examservice.entity.Score;
import com.example.examservice.entity.StudentAnswer;
import com.example.examservice.repository.ChoiceRepository;
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

    private final ChoiceRepository choiceRepository;

    @Autowired
    public ExamQuestionService(StudentAnswerRepository studentAnswerRepository, ExamQuestionRepository examQuestionRepository, ScoreRepository scoreRepository, ChoiceRepository choiceRepository) {
        this.studentAnswerRepository = studentAnswerRepository;
        this.examQuestionRepository = examQuestionRepository;
        this.scoreRepository = scoreRepository;
        this.choiceRepository = choiceRepository;
    }

    public String takeExam(String password, Integer userId, StudentAnswerRequest studentAnswerRequest) {

        List<ExamQuestion> examQuestions = examQuestionRepository.findByExamPassword(password);

        examQuestions.stream().forEach(
                examQuestion -> {
                    StudentAnswer studentAnswer = new StudentAnswer();

                    studentAnswer.setExam(examQuestion);
                    studentAnswer.setUserId(userId);
                    studentAnswer.setAnswer(studentAnswerRequest.getChoise_text());

                    studentAnswerRepository.save(studentAnswer);

                    Score score = new Score();


                    score.setExam(examQuestion.getExam());
                    score.setUserId(userId);

                    if (choiceRepository.findByChoiceText(studentAnswerRequest.getChoise_text()).getCorrect().equals(Boolean.TRUE)) {
                        score.setScore(+1);
                    }

                    scoreRepository.save(score);
                }
        );

        return "Take Exam";
    }
}
