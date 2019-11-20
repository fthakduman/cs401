package base.user.service;

import base.user.model.UserImpl;

import java.util.List;

public interface UserService {

    UserImpl findById(String id);

    UserImpl findUserByUserName(String username);

    UserImpl saveUser(UserImpl userImpl);

    UserImpl updateUser(UserImpl userImpl);

    void deleteUserById(String id);

    List<UserImpl> findAllUsers();

    void deleteAllUsers();

    boolean isUserExist(UserImpl userImpl);

}