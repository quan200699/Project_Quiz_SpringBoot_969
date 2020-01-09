package com.codegym.quiz.service.impl;

import com.codegym.quiz.model.Category;
import com.codegym.quiz.model.Question;
import com.codegym.quiz.model.Quiz;
import com.codegym.quiz.model.TypeOfQuestion;
import com.codegym.quiz.repository.QuestionRepository;
import com.codegym.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Iterable<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public void save(Question model) {
        questionRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public Iterable<Question> findAllByCategoryAndStatusIsTrue(Category category) {
        return questionRepository.findAllByCategoryAndStatusIsTrue(category);
    }

    @Override
    public Iterable<Question> findAllByTypeOfQuestionAndStatusIsTrue(TypeOfQuestion typeOfQuestion) {
        return questionRepository.findAllByTypeOfQuestionAndStatusIsTrue(typeOfQuestion);
    }

    @Override
    public Iterable<Question> findAllByTypeOfQuestionAndCategoryAndStatusIsTrue(TypeOfQuestion typeOfQuestion, Category category) {
        return questionRepository.findAllByTypeOfQuestionAndCategoryAndStatusIsTrue(typeOfQuestion, category);
    }

    @Override
    public Iterable<Question> findAllByStatusIsTrue() {

        return questionRepository.findAllByStatusIsTrue();
    }

    @Override
    public Iterable<Question> findAllByQuiz(Quiz quiz) {

        return questionRepository.findAllByQuiz(quiz);
    }

    @Override
    public Iterable<Question> findAllByQuizIsNullAndStatusIsTrue() {
        return questionRepository.findAllByQuizIsNullAndStatusIsTrue();
    }

    @Override
    public Iterable<Question> findAllByContentContainingAndStatusIsTrue(String content) {
        return questionRepository.findAllByContentContainingAndStatusIsTrue(content);
    }

    @Override
    public Iterable<Question> findAllByContentContainingAndTypeOfQuestionAndCategoryAndStatusIsTrue(String content, TypeOfQuestion typeOfQuestion, Category category) {
        return questionRepository.findAllByContentContainingAndTypeOfQuestionAndCategoryAndStatusIsTrue(content,typeOfQuestion,category);
    }

    @Override
    public Iterable<Question> findAllByContentContainingAndCategoryAndStatusIsTrue(String content, Category category) {
        return questionRepository.findAllByContentContainingAndCategoryAndStatusIsTrue(content,category);
    }

    @Override
    public Iterable<Question> findAllByContentContainingAndTypeOfQuestionAndStatusIsTrue(String content, TypeOfQuestion typeOfQuestion) {
        return questionRepository.findAllByContentContainingAndTypeOfQuestionAndStatusIsTrue(content,typeOfQuestion);
    }

    @Override
    public Iterable<Question> findAllByQuizIsNullAndContentContainingAndStatusIsTrue(String content) {
        return questionRepository.findAllByQuizIsNullAndContentContainingAndStatusIsTrue(content);
    }

    @Override
    public Iterable<Question> findAllByQuizIsNullAndCategoryAndStatusIsTrue(Category category) {
        return questionRepository.findAllByQuizIsNullAndCategoryAndStatusIsTrue(category);
    }

    @Override
    public Iterable<Question> findAllByQuizIsNullAndTypeOfQuestionAndStatusIsTrue(TypeOfQuestion typeOfQuestion) {
        return questionRepository.findAllByQuizIsNullAndTypeOfQuestionAndStatusIsTrue(typeOfQuestion);
    }

    @Override
    public Iterable<Question> findAllByQuizIsNullAndCategoryAndTypeOfQuestionAndStatusIsTrue(Category category, TypeOfQuestion typeOfQuestion) {
        return questionRepository.findAllByQuizIsNullAndCategoryAndTypeOfQuestionAndStatusIsTrue(category,typeOfQuestion);
    }

    @Override
    public Iterable<Question> findAllByQuizIsNullAndContentContainingAndCategoryAndStatusIsTrue(String content, Category category) {
        return questionRepository.findAllByQuizIsNullAndContentContainingAndCategoryAndStatusIsTrue(content,category);
    }

    @Override
    public Iterable<Question> findAllByQuizIsNullAndContentContainingAndTypeOfQuestionAndStatusIsTrue(String content, TypeOfQuestion typeOfQuestion) {
        return questionRepository.findAllByQuizIsNullAndContentContainingAndTypeOfQuestionAndStatusIsTrue(content,typeOfQuestion);
    }

    @Override
    public Iterable<Question> findAllByQuizIsNullAndContentContainingAndCategoryAndTypeOfQuestionAndStatusIsTrue(String content, Category category, TypeOfQuestion typeOfQuestion) {
        return questionRepository.findAllByQuizIsNullAndContentContainingAndCategoryAndTypeOfQuestionAndStatusIsTrue(content,category,typeOfQuestion);
    }

}
