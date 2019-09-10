package com.cf.user.reponsitory;

import com.cf.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2019/5/5 0005.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
