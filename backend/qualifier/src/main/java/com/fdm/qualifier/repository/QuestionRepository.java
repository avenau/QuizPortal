package com.fdm.qualifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdm.qualifier.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
