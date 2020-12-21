package com.example.demo.controller;

import com.example.demo.common.Common;
import com.example.demo.common.Constants;
import com.example.demo.entity.QuestionEntity;
import com.example.demo.entity.TestEntity;
import com.example.demo.entity.bo.BaseMessage;
import com.example.demo.entity.bo.ResponseEntityBO;
import com.example.demo.service.impl.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tests")

public class TestController {
	@Autowired
	TestService testService;
	Logger logger = Logger.getLogger("TestController");
	long timeStamp;
	BaseMessage response;
	//hiển thi danh sách bài thi
	@GetMapping
	public ResponseEntity<?> findAll() {
		timeStamp = Common.getTimeStamp();
		try {
			List<TestEntity> lst = testService.findAll();
			if (Common.isNullOrEmpty(lst)) {
				response = new BaseMessage(Constants.ERROR_RESPONSE, "Không có bài thi!", timeStamp);
				logger.error(Common.createMessageLog(null, response, Common.getUserName(), timeStamp, "findAll"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			} else {
				response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, lst);
				logger.info(Common.createMessageLog(null, response, Common.getUserName(), timeStamp, "findAll"));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, e.getMessage(), timeStamp);
			logger.error(Common.createMessageLog(null, response, Common.getUserName(), timeStamp, "findAll"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

		}
	}
	//hiển thị bài thi theo id
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@Valid @PathVariable Integer id) {
		timeStamp = Common.getTimeStamp();
		try {
			Optional<TestEntity> testEntity = testService.findById(id);
			response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, testEntity.get());
			logger.info(Common.createMessageLog(id, response, Common.getUserName(), timeStamp, "findbyId"));
			return ResponseEntity.status(HttpStatus.OK).body(response);

		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "Không tìm thấy id bài thi", timeStamp);
			logger.error(Common.createMessageLog(id, response, Common.getUserName(), timeStamp, "findByid"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	//thêm bài thi
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody TestEntity test) {
		timeStamp = Common.getTimeStamp();
		try {
			response = new BaseMessage(Constants.SUCCESS_RESPONSE, "Thêm thành công", timeStamp);
			testService.save(test);
			logger.info(Common.createMessageLog(test, response, Common.getUserName(), timeStamp, "save"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "Không thể thêm bài thi", timeStamp);
			logger.error(Common.createMessageLog(test, response, Common.getUserName(), timeStamp, "save"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	//xóa bài thi
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		timeStamp = Common.getTimeStamp();
		try {
			Optional<TestEntity> test = testService.findById(id);
			response = new BaseMessage(Constants.SUCCESS_RESPONSE, "Xóa thành công", timeStamp);
			testService.deleteTestById(id);
			logger.info(Common.createMessageLog(id, response, Common.getUserName(), timeStamp, "deleteById"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "Không tìm thấy id bài thi", timeStamp);
			logger.error(Common.createMessageLog(id, response, Common.getUserName(), timeStamp, "deleteById"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	//update bài thi
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody TestEntity test, @Valid @PathVariable Integer id) {
		timeStamp = Common.getTimeStamp();
		try {
			testService.findById(id);
			response = new BaseMessage(Constants.SUCCESS_RESPONSE, "Sửa Thành công", timeStamp);
			testService.updateTest(test,id);
			logger.info(Common.createMessageLog(test, response, Common.getUserName(), timeStamp, "update"));
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "không tìm thấy id bài thi", timeStamp);
			logger.error(Common.createMessageLog(test, response, Common.getUserName(), timeStamp, "update"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}
	//thêm user vào bài thi
	@PostMapping("/add/{id_test}/users")
	public BaseMessage addListUserWithTest(@RequestBody List<Integer> idListUserRequests, @PathVariable Integer id_test) {
		BaseMessage response;
		long timeStamp = Common.getTimeStamp();
		try {
			String results = testService.addListTestWithUser(idListUserRequests, id_test);

			response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results);
			logger.info(Common.createMessageLog(idListUserRequests, response, Common.getUserName(), timeStamp, "getMultipleChoiceScores"));

		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
			logger.error(Common.createMessageLog(idListUserRequests, response, Common.getUserName(), timeStamp, "getMultipleChoiceScores"));
		}
		return response;
	}
	//thêm câu hỏi vào bài thi
	@PostMapping("/add/{id_test}/tests")
	public BaseMessage addListQuestionsWithTest(@RequestBody List<Integer> idListQuestion, @PathVariable Integer id_test) {
		BaseMessage response;
		long timeStamp = Common.getTimeStamp();
		try {
			String results = testService.addListQuestionsWithTest(idListQuestion, id_test);

			response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results);
			logger.info(Common.createMessageLog(idListQuestion, response, Common.getUserName(), timeStamp, "getMultipleChoiceScores"));

		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
			logger.error(Common.createMessageLog(idListQuestion, response, Common.getUserName(), timeStamp, "getMultipleChoiceScores"));
		}
		return response;

	}
}