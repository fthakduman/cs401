package base.user.service;

import base.user.model.UserImpl;

import java.util.Collection;
import java.util.List;

public interface UserService {

    UserImpl findById(String id);

    List<UserImpl> findUserByUserName(String username);

    UserImpl saveUser(UserImpl userImpl);

    UserImpl updateUser(String id,UserImpl userImpl);

    UserImpl updateUserPassword(String id,UserImpl userImpl);

    UserImpl updateUserRole(  String id ,UserImpl userRecord );

    void deleteUserById(String id);

    Collection<UserImpl> findAllUsers();

    void deleteAllUsers();

    boolean isUserExist(UserImpl userImpl);

}