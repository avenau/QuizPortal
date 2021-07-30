package com.fdm.qualifier.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;

import com.fdm.qualifier.model.SuggestedSkill;

import com.fdm.qualifier.service.QuizService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
	private QuizService quizService;

	@Autowired
	public QuizController(QuizService quizService) {
		super();
		this.quizService = quizService;
	}
	
	/*@PostMapping("/saveSuggestedSkill")
	public void save(SuggestedSkill suggestedSkill) {
		log.trace("save() called");
		log.info("Saving suggested skill: " + suggestedSkill);
		suggestedSkillService.save(suggestedSkill);
	}*/
	
	@GetMapping("/getStartQuizDetails")
	public Quiz startQuizDetails(int id) {
		System.out.println("ID adfd: " + id);
		Optional<Quiz> selectedQuiz = quizService.findQuizById(id);
		if (!selectedQuiz.isPresent()) {
			System.out.println("ERROR");
			return null;
		}
		return selectedQuiz.get();
	}


}
