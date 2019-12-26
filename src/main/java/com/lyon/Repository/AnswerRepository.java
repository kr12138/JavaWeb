package com.lyon.Repository;

import com.lyon.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByTid(long tid);
    List<Answer> findByQid(long qid);
}
