package org.yonixee.service;

import org.yonixee.entity.User;

import java.util.List;

/**
 * Created by yonixee on 15/5/7.
 */
public interface UserService {

    public void saveUser(User user);

    public void updateUser(User user);

    public User findUserById(int id);

    public void deleteUser(User user);

    public List<User> findAllList();

    public User findUserByNameAndPassword(String username, String password);
}
