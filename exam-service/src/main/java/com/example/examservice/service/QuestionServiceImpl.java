package com.example.examservice.service;


import com.example.examservice.dto.AddCourseIdToQuestionRequest;
import com.example.examservice.dto.QuestionRequest;
import com.example.examservice.entity.Category;
import com.example.examservice.entity.Exam;
import com.example.examservice.entity.Question;
import com.example.examservice.repository.CategoryRepository;
import com.example.examservice.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String addCourseIdToQuestion(AddCourseIdToQuestionRequest addCourseIdToQuestionRequest) {

        Question question = new Question();

        question.setCourseId(addCourseIdToQuestionRequest.getCourseId());

        questionRepository.save(question);

        return "Add Course Id to Question";
    }

    @Override
    public String createQuestion(QuestionRequest questionRequest, int id) {

        Question question = questionRepository.getById(id);

        Category category = categoryRepository.getById(id);

        question.setName(questionRequest.getName());
        question.setCategory(category);
        question.setQuestionText(questionRequest.getQuestionText());

        questionRepository.save(question);

        return "Add Question";
    }
}
