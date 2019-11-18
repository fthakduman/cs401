package base.user.service;

import base.user.model.User;

import java.util.List;

public interface UserService {

    User findById(String id);

    User findUserByUserName(String username);

    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(String id);

    List<User> findAllUsers();

    void deleteAllUsers();

    boolean isUserExist(User user);

}