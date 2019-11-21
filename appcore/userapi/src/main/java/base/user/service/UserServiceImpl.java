package base.user.service;

import base.user.model.UserImpl;
import base.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserImpl findById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public UserImpl findUserByUserName(String username) {
        return (UserImpl) userRepository.findUserImplByUserName(username);
    }

    @Override
    public UserImpl saveUser(UserImpl userImpl) {
        return userRepository.save(userImpl);
    }

    @Override
    public UserImpl updateUser(UserImpl userImpl) {
        return userImpl;
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteUserImplById(id);
    }

    @Override
    public List<UserImpl> findAllUsers() {
        return (List<UserImpl>) userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public boolean isUserExist(UserImpl userImpl) {
        return userRepository.exists(userImpl.getId());
    }


}