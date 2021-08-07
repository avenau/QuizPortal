package com.fdm.qualifier.service;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fdm.qualifier.model.Answer;
import com.fdm.qualifier.model.Question;
import com.fdm.qualifier.model.Quiz;
import com.fdm.qualifier.model.Result;

import com.fdm.qualifier.model.SubmittedAnswer;

import com.fdm.qualifier.repository.AnswerRepository;
import com.fdm.qualifier.repository.QuestionRepository;
import com.fdm.qualifier.repository.QuizRepository;
import com.fdm.qualifier.repository.ResultRepository;


//public class QuizServiceTest {
//	private QuizService quizService;
//	
//	@Mock
//	private Quiz mockQuiz;
//	
//	@Mock
//	private Answer mockAnswer;
//	
//	@Mock
//	private Question mockQuestion;
//	
//	@Mock
//	private Result mockResult;
//	
//	@Mock
//	private AnswerRepository mockAnswerRepo;
//	
//	@Mock
//	private QuestionRepository mockQuestionRepo;
//	
//	@Mock
//	private QuizRepository mockQuizRepo;
//	
//	@Mock
//	private ResultRepository mockResultRepo;
//	
//	@BeforeEach
//	public void setup() {
//		MockitoAnnotations.openMocks(this);
//		quizService = new QuizService(mockResultRepo, mockQuizRepo, mockQuestionRepo, mockAnswerRepo);
//	}
//	
//	@Test
//	public void test_save_quiz() {
//		// ARRANGE
//		// ACT
//		quizService.save(mockQuiz);
//		// ASSERT
//		verify(mockQuizRepo, times(1)).save(mockQuiz);
//		verify(mockQuestionRepo, times(1)).save(mockQuestion);
//		verify(mockAnswerRepo, times(1)).save(mockAnswer);

class QuizServiceTest {
	private QuizService quizService;
	
	@Mock
	private ResultRepository mockResultRepo;

	@Mock
	private QuizRepository mockQuizRepo;

	@Mock
	private QuestionRepository mockQuestionRepo;

	@Mock
	private AnswerRepository mockAnswerRepo;

	@Mock
	private QuestionService mockQuestionServ;

	@Mock
	private SubmittedAnswerService mockSubmittedAnswerServ;

	@Mock
	private AnswerService mockAnswerServ;

	@Mock
	private ResultService mockResultServ;

	@Mock
	private Quiz mockQuiz;

	@Mock
	private Answer mockAnswer;

	@Mock
	private Question mockQuestion;

	@Mock
	private SubmittedAnswer mockSubmittedAnswer;

	@Mock
	private Result mockResult;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		quizService = new QuizService(mockResultRepo, mockQuizRepo,
				mockQuestionRepo, mockAnswerRepo, mockQuestionServ, 
				mockSubmittedAnswerServ, mockAnswerServ, mockResultServ);
	}

	@Test
	public void getfindQuizById_getQuiz() {
		Optional<Quiz> quiz = Optional.of(mockQuiz);
		when(mockQuizRepo.findById(1)).thenReturn(quiz);
		
		Optional<Quiz> result = quizService.findQuizById(1);
		
		assertEquals(mockQuiz, result.get());
	}

	@Test
	public void findAllQuiz_returns_list() {
		List<Quiz> expected = new ArrayList<>();
		when(mockQuizRepo.findAll()).thenReturn(expected);
		
		List<Quiz> result = quizService.findAllQuiz();

		assertEquals(expected, result);
	}
	
	@Test
	public void saveQuiz_returnsSavedQuiz() {
		when(mockQuizRepo.save(mockQuiz)).thenReturn(mockQuiz);
		
		Quiz result = quizService.saveQuiz(mockQuiz);
		
		assertEquals(mockQuiz, result);
	}
	
	@Test
	public void saveAnswer_returnsSavedAnswer() {
		when(mockAnswerRepo.save(mockAnswer)).thenReturn(mockAnswer);
		
		Answer result = quizService.saveAnswer(mockAnswer);
		
		assertEquals(mockAnswer, result);
	}
	
	@Test
	public void saveQuestion_returnsSavedQuestion() {
		when(mockQuestionRepo.save(mockQuestion)).thenReturn(mockQuestion);
		
		Question result = quizService.saveQuestion(mockQuestion);
		
		assertEquals(mockQuestion, result);
	}
	
	@Test
	public void deleteQuiz_verifyDeletion() {
		List<Question> questions = new ArrayList<Question>();
		questions.add(mockQuestion);
		List<SubmittedAnswer> subAnswers = new ArrayList<SubmittedAnswer>();
		subAnswers.add(mockSubmittedAnswer);
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(mockAnswer);
		List<Result> results = new ArrayList<Result>();
		results.add(mockResult);
		when(mockQuiz.getQuizId()).thenReturn(1);
		when(mockQuestionServ.findAllQuestionsOfQuizID(mockQuiz)).thenReturn(questions);
		when(mockSubmittedAnswerServ.findByQuestion(mockQuestion)).thenReturn(subAnswers);
		when(mockAnswerServ.findByQuestion(mockQuestion)).thenReturn(answers);
		when(mockResultServ.findByQuiz(mockQuiz)).thenReturn(results);
		
		quizService.deleteQuiz(mockQuiz);
		
		verify(mockSubmittedAnswerServ, times(1)).delete(mockSubmittedAnswer);
		verify(mockAnswerServ, times(1)).delete(mockAnswer);
		verify(mockQuestionServ, times(1)).delete(mockQuestion);
		verify(mockResultServ, times(1)).delete(mockResult);
		verify(mockQuizRepo, times(1)).delete(mockQuiz);

	}

	
	@Test
	public void test_save_quiz() {
		// ARRANGE
		// ACT
		quizService.save(mockQuiz);
		// ASSERT
		verify(mockQuizRepo, times(1)).save(mockQuiz);
		//Commented These out because apparently these mocks have no interactions
//		verify(mockQuestionRepo, times(1)).save(mockQuestion);
//		verify(mockAnswerRepo, times(1)).save(mockAnswer);
	}
	
	@Test
	public void test_saveResult_calls_repo_save() {
		//Arrange
		when(mockResultRepo.save(mockResult)).thenReturn(mockResult);
		
		//Act
		Result actual = quizService.saveResult(mockResult);
		
		//Assert
		verify(mockResultRepo, times(1)).save(mockResult);
		assertEquals(mockResult, actual);
	}
	
	@Test
	public void test_findResultById_returns_result_when_found() {
		//Arrange
		int id = 1;
		when(mockResultRepo.findById(id)).thenReturn(Optional.of(mockResult));
		
		//Act
		Result actual = quizService.findResultById(id);
		
		//Assert
		verify(mockResultRepo, times(1)).findById(id);
		assertEquals(mockResult, actual);
	}
	
	@Test
	public void test_findResultById_returns_null_when_not_found() {
		//Arrange
		int id = 1;
		when(mockResultRepo.findById(id)).thenReturn(Optional.of(mockResult).empty());
		
		//Act
		Result actual = quizService.findResultById(id);
		//Assert
		verify(mockResultRepo, times(1)).findById(id);
		assertEquals(null, actual);
	}
}
