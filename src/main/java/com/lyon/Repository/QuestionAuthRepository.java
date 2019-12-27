package com.lyon.Repository;

import com.lyon.Entity.QuestionAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionAuthRepository extends JpaRepository<QuestionAuth, Long> {

    List<QuestionAuth> findByTid(long tid);

    List<QuestionAuth> findBySid(long sid);

    QuestionAuth findBySidAndTid(long sid, long tid);
    @Transactional
    void deleteBySidAndTid(long sid, long tid);

    boolean existsByTid(long tid);

    boolean existsBySid(long sid);

    boolean existsByTidAndSid(long tid, long sid);
}
