package com.example.demo.controller;

import com.example.demo.common.Common;
import com.example.demo.common.Constants;
import com.example.demo.entity.NoticeOutput;
import com.example.demo.entity.QuestionEntity;
import com.example.demo.entity.TaskEntity;
import com.example.demo.entity.bo.BaseMessage;
import com.example.demo.entity.bo.ResponseEntityBO;
import com.example.demo.payload.request.EssayScoringRequest;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.ITaskService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@Autowired
	ITaskService taskService;
	@Autowired
	TaskRepository taskRepository;
	Logger logger = Logger.getLogger("QuestionController");

	@PostMapping("/{test_id}")
	public BaseMessage sendAnswer(@RequestBody List<QuestionEntity> questionEntityList, @PathVariable Integer test_id) {
		BaseMessage response;
		long timeStamp = Common.getTimeStamp();
		try {
			String resultTask = taskService.sendAnswer(questionEntityList, test_id);
			response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, resultTask);
			logger.info(Common.createMessageLog(questionEntityList, response, Common.getUserName(), timeStamp, "findTaskResutl"));

		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
			logger.error(Common.createMessageLog(questionEntityList, response, Common.getUserName(), timeStamp, "findTaskResutl"));
		}
		return response;
	}
	@GetMapping("/test/{test_id}/user/{user_id}")
	public BaseMessage getAllTask(@PathVariable Integer user_id, @PathVariable Integer test_id) {
		BaseMessage response;
		long timeStamp = Common.getTimeStamp();
		try {
			List<Map<String, Object>> resultTask = taskService.findAllByTask(user_id, test_id);
			response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, resultTask);
			logger.info(Common.createMessageLog(null, response, Common.getUserName(), timeStamp, "findTaskResutl"));

		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, e.getMessage(), timeStamp);
			logger.error(Common.createMessageLog(null, response, Common.getUserName(), timeStamp, "findTaskResutl"));
		}
		return response;
	}



	//    @GetMapping("/multiplechoiceresults/{testName}")
//    public BaseMessage getMarkOnTotalQuestion(@PathVariable String testName){
//        BaseMessage response;
//        long timeStamp = Common.getTimeStamp();
//        try {
//            Object object= taskService.getMarkOnTotalQuestion(testName);
//            System.out.println(object.toString());
//            response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, object);
//            logger.info(Common.createMessageLog(testName, response, Common.getUserName(), timeStamp, "getMarkOnTotalQuestion"));
//
//        } catch (Exception e) {
//            response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
//            logger.error(Common.createMessageLog(testName, response, Common.getUserName(), timeStamp, "getMarkOnTotalQuestion"));
//        }
//        return response;
//    }
	//lay diem bai test tu luan cua user dang nhap
	@GetMapping("/essayscoreresults/{test_id}")
	public BaseMessage getEssayScoreResults(@PathVariable Integer test_id) {
		BaseMessage response;
		long timeStamp = Common.getTimeStamp();
		try {
			String results = taskService.getEssayScoreResults(test_id);

			response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results);
			logger.info(Common.createMessageLog(test_id, response, Common.getUserName(), timeStamp, "getEssayScoreResults"));

		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
			logger.error(Common.createMessageLog(test_id, response, Common.getUserName(), timeStamp, "getEssayScoreResults"));
		}
		return response;
	}

	//cham diem bai test cau tu luan
	@PutMapping("/essayscoreresults/test/{test_id}/user/{user_id}")
	public BaseMessage essayScoring(@RequestBody List<EssayScoringRequest> essayScoringRequest, @PathVariable Integer test_id, @PathVariable Integer user_id) {
		BaseMessage response;
		long timeStamp = Common.getTimeStamp();
		try {
			String results = taskService.essayScoring(essayScoringRequest, test_id, user_id);

			response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results);
			logger.info(Common.createMessageLog(essayScoringRequest, response, Common.getUserName(), timeStamp, "essayScoring"));

		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
			logger.error(Common.createMessageLog(essayScoringRequest, response, Common.getUserName(), timeStamp, "essayScoring"));
		}
		return response;
	}

	//lay diem trac nhiem
	@GetMapping("/result/test/{test_id}")
	public BaseMessage getMultipleChoiceScores( @PathVariable Integer test_id) {
		BaseMessage response;
		long timeStamp = Common.getTimeStamp();
		try {
			String results = taskService.getMultipleChoiceScores(test_id);

			response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results);
			logger.info(Common.createMessageLog(test_id, response, Common.getUserName(), timeStamp, "getEssayScoreResults"));

		} catch (Exception e) {
			response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
			logger.error(Common.createMessageLog(test_id, response, Common.getUserName(), timeStamp, "getEssayScoreResults"));
		}
		return response;
	}

}
