package userapi.base.service;

import userapi.base.model.UserImpl;
import userapi.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserImpl findById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public  List<UserImpl>findUserByUserName(String username) {
        return userRepository.findUserImplByUserName(username);
    }

    @Override
    public UserImpl saveUser(UserImpl userImpl) throws EntityExistsException {
        userImpl.setPassword(bCryptPasswordEncoder.encode(userImpl.getPassword()));
        if(!userRepository.findUserImplByUserName(userImpl.getUserName()).isEmpty()){
            throw new EntityExistsException("User exists with the user name");
        }
        else{
            return userRepository.save(userImpl);
        }
    }



    @Override
    public UserImpl updateUser(String id, UserImpl userImpl) {
        UserImpl userInfo = userRepository.findOne(id);
        userInfo.setUserName(userImpl.getUserName());
        userInfo.setPassword(userImpl.getPassword());
        userInfo.setUserRole(userImpl.getUserRole());
        return saveUser(userInfo);
    }
    @Override
    public UserImpl updateUserPassword(String id, UserImpl userImpl) {
        UserImpl userInfo = userRepository.findOne(id);
        userInfo.setPassword(userImpl.getPassword());
        return saveUser(userInfo);
    }

    @Override
    public UserImpl updateUserRole(String id, UserImpl userRecord) {
        UserImpl userInfo = userRepository.findOne(id);
        userInfo.setUserRole(userRecord.getUserRole());
        return saveUser(userInfo);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.delete(id);
    }

    @Override
    public List<UserImpl> findAllUsers() {
        List<UserImpl> result = new ArrayList<UserImpl>();
       userRepository.findAll().forEach(item ->
              result.add(item) );
       return result;
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