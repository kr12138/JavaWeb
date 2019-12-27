package com.lyon.Repository;

import com.lyon.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findById(long aid);
    List<Answer> findByTid(long tid);
    List<Answer> findByQid(long qid);
    List<Answer> findByQidAndRead(long qid, boolean read);
}
