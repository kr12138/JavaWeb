package com.lyon.Repository;

import com.lyon.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(long id);
    List<Question> findByCid(long cid);
}
