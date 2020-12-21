package com.example.demo.service;

import com.example.demo.entity.TestEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ITestService {
	public List<TestEntity> findAll();

	public Optional<TestEntity> findById(Integer id);

	public void save(TestEntity testEntity);

	public void deleteTestById(Integer id);

	public void updateTest(TestEntity testEntity, Integer id);

	String addListTestWithUser(List<Integer> idListUserRequest, Integer testId);

	String addListQuestionsWithTest(List<Integer> idListQuestionsTest, Integer testId);

}
