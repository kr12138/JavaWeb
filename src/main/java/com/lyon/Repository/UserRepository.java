package com.lyon.Repository;

import com.lyon.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByIdAndPasswordAndIdentity(long id, String password, short identity);
    User findByIdAndIdentity(long id, short identity);
    User findById(long id);

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE `user` SET `password`=?1 WHERE `u_id`=?2",nativeQuery = true)
//    void updateUserById(String password, long id);

//    default void updateUserById(String password, long id) {
//        String hql = "update User u set u.password=:password where u.u_id=:id";
//        Session session = .openSession();
//        Query query =
//    }

}