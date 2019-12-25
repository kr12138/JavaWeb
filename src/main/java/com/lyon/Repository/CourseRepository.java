package com.lyon.Repository;

import com.lyon.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findById(long id);
    List<Course> findByDept(String dept);
}
