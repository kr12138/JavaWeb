package com.lyon.Repository;

import com.lyon.Entity.Dept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptRepository extends JpaRepository<Dept, Long> {

    Dept findById(long id);
//    List<Dept> findAll(Sort sort);
//    Page<Dept> findAll(Pageable pageable);

}
