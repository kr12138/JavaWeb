package com.lyon.Repository;

import com.lyon.Entity.Teaches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeachesRepository extends JpaRepository<Teaches, Long> {
    List<Teaches> findByUid(long uid);
    List<Teaches> findByCid(long cid);
    Teaches findByUidAndCid(long uid, long cid);
    @Transactional
    void deleteByUidAndCid(long uid, long cid);
}
