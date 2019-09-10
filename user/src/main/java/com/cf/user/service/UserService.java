package com.cf.user.service;

import com.cf.user.pojo.User;
import com.cf.user.reponsitory.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Administrator on 2019/5/5 0005.
 */
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManger;
//    @Resource
//    private SessionFactory sessionFactory;

    public User findByName(String name) {
//        return userRepository.findByName(name);
        return userRepository.findById(Long.parseLong(name)).get();
    }

    @Transactional
    public List<User> find() throws Exception {
        User user = new User();
//        user.setId(13L);
        user.setName("测试hivernage事务");
        entityManger.unwrap(Session.class).save(user);
        throw new Exception("的");
//        return entityManger.unwrap(Session.class)
//                .createNativeQuery("select id,name from tb_user")
//                .addEntity(User.class).getResultList();
//       return entityManger.createNativeQuery("select id,name from tb_user").getResultList();
//       return sessionFactory.getCurrentSession().createQuery("select id,name from tb_user", User.class).list();
    }
}
