package provider.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import provider.base.model.RankRequest;
import provider.base.util.CommonUtils;
import userapi.base.model.UserImpl;
import userapi.base.model.UserRole;
import userapi.base.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


public class UserChecker implements UserCheck {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean isUserExist(RankRequest request){
        return userRepository.exists(userRepository.findUserImplByUserName(request.getUserName()).get(0).getId());
    }
    public boolean isPasswordValid(RankRequest request){
        UserImpl userFromDB = userRepository.findUserImplByUserName(request.getUserName()).get(0);
        return bCryptPasswordEncoder.matches(request.getPassword(), userFromDB.getPassword());
    }
    public boolean isBankNumberValid(RankRequest request){
        UserImpl userFromDB = userRepository.findUserImplByUserName(request.getUserName()).get(0);
        if(userFromDB.getUserRole().equals(UserRole.STANDART_ROLE) && request.getBankNames().get(0).keySet().size() > 2){
            return false;
        }
        return true;
    }
    public boolean isBankNameValid(RankRequest request){
        Collection<String> bankNames = request.getBankNames().get(0).values();
        for(String name : bankNames){
            if(!name.contentEquals(CommonUtils.ykb) && !name.contentEquals(CommonUtils.dnz) && !name.contentEquals(CommonUtils.isb)){
                return false;
            }
        }
        return true;
    }
    public boolean isRequestKeyValid(RankRequest request){
        Collection<String> bankNames = request.getBankNames().get(0).keySet();
        for(String key : bankNames){
            if(!key.contentEquals("bankName")){
                return false;
            }
        }
        return true;
    }

}
