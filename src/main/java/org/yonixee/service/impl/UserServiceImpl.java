package org.yonixee.service.impl;

import org.springframework.stereotype.Service;
import org.yonixee.dao.BaseDao;
import org.yonixee.entity.User;
import org.yonixee.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yonixee on 15/5/7.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private BaseDao<User> baseDao;


    @Override
    public void saveUser(User user) {
        baseDao.save(user);
    }
    

    @Override
    public void updateUser(User user) {
        baseDao.update(user);
    }

    @Override
    public User findUserById(int id) {
        return baseDao.get(User.class, id);
    }

    @Override
    public void deleteUser(User user) {
        baseDao.delete(user);
    }

    @Override
    public List<User> findAllList() {
        return baseDao.find(" from User u order by u.createTime");
    }

    @Override
    public User findUserByNameAndPassword(String username, String password) {
        return baseDao.get(" from User u where u.userName = ? and u.password = ? ", new Object[]{username, password});
    }

}
