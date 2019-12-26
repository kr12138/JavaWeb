package com.lyon.Repository;

import com.lyon.Entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findById(long id);
    List<Question> findByCid(long cid);
    Page<Question> findById(long id, Pageable pageable);
    Page<Question> findByTitleContaining(String title, Pageable pageable);
    Page<Question> findByContentContaining(String content, Pageable pageable);
//    List<Question> findAllByTitleLike(String title);
}
