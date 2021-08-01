import { useState, useEffect } from "react";
import {useLocation, useHistory} from "react-router-dom";
import { Form, Check } from 'react-bootstrap';

function AttemptQuizPage() {

    const history = useHistory();
    const quizId = useLocation().pathname.split("/")[2];
    const axios = require("axios");
    const questionTemplate = 
                     [{           
                        questionId: 0,
                        content: "NOT LOADED",
                        type: "NOT LOADED",
                        points: 0,
                        image: "NOT LOADED",
                        answers:  [{
                            answerId: 0,
                            content: "NOT LOADED",
                            correct: "NOT LOADED"
                        }]
                    }]
  
    const [questions, setQuestion] = useState(questionTemplate);
    const [answerCounter, setAnswerCounter] = useState(0);

    useEffect (() => {
        axios
        .get('http://localhost:9999/getQuizQuestions', {   
            params: {
                id:quizId
            },
        })
        .then((response) =>{
            setQuestion(response.data);
        })
        .catch((error) => {
            history.push("/*");
        })
        console.log(questions);

    }, [questions.length]);


    return (
        <div>
            <p>HELLO</p>
            <p>{questions.length}</p>
            <Form>
                {['checkbox', 'radio'].map((type) => (
                    <div key={`inline-${type}`} className="mb-3">
                    <Form.Check
                        inline
                        label="1"
                        name="group1"
                        type={type}
                        id={`inline-${type}-1`}
                    />
                    <Form.Check
                        inline
                        label="2"
                        name="group1"
                        type={type}
                        id={`inline-${type}-2`}
                    />
                    <Form.Check
                        inline
                        disabled
                        label="3 (disabled)"
                        type={type}
                        id={`inline-${type}-3`}
                    />
                    </div>
                ))}
            </Form>
            <Form>
                {questions.map(question => (
                        

                    <p>
                        Question id: {question.questionId} <br/>
                        Question Content: {question.content} <br/>
                        <div key={`inline-${question.type}`} className="mb-3">

                            {question.answers.map(answer =>(
                                <div>
                                    {(() => {
                                        {
                                            if (question.type === "MUTIPLE_CHOICE") {
                                                
                                                return( 
                                                    <Form.Check
                                                    inline
                                                    label={answer.content}
                                                    name={answer.answerId}
                                                    type="radio"
                                                    id={`inline-radio-${answerCounter}`}
                                                    />
                                                )
                                            } else if (question.type === "MULTI_SELECT"){
                                                return(
                                                    <Form.Check
                                                    inline
                                                    label={answer.content}
                                                    name={answer.answerId}
                                                    type="checkbox"
                                                    id={`inline-checkbox-${answerCounter}`}
                                                    /> 
                                                )
                                            } else if (question.type === "SHORT_ANSWER"){
                                                return(
                                                    <Form.Check
                                                    inline
                                                    label={answer.content}
                                                    name={answer.answerId}
                                                    type="checkbox"
                                                    id={`inline-checkbox-${answerCounter}`}
                                                    /> 
                                                )
                                            }
                                        }
                                        
                                    })()}
                                </div>
                                
                            ))}
                        </div>
                    </p>

                )) }
            </Form>
        </div>
    )
}
export default AttemptQuizPage;