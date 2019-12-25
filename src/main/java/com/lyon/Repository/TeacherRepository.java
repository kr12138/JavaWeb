package com.lyon.Repository;

import com.lyon.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findById(long id);
    Teacher findByName(String name);
}
