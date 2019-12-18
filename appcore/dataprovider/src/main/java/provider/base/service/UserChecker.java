package provider.base.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.schema.JsonSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import provider.base.model.RankRequest;
import provider.base.util.CommonUtils;
import userapi.base.model.UserImpl;
import userapi.base.model.UserRole;
import userapi.base.repository.UserRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Qualifier("checker")
public class UserChecker implements UserCheck {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean isUserExist(RankRequest request) {
        try {
            userRepository.findUserImplByUserName(request.getUserName()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public boolean isPasswordValid(RankRequest request) {
        UserImpl userFromDB = userRepository.findUserImplByUserName(request.getUserName()).get(0);
        return bCryptPasswordEncoder.matches(request.getPassword(), userFromDB.getPassword());
    }

    public boolean isBankNumberValid(RankRequest request) {

        UserImpl userFromDB = userRepository.findUserImplByUserName(request.getUserName()).get(0);
        if (userFromDB.getUserRole().equals(UserRole.STANDART_ROLE) && request.getBankNames().size() > 2) {
            return false;
        }
        return true;
    }

    public boolean isBankNameValid(RankRequest request) {
        Collection<String> bankNames = request.getBankNames().get(0).values();
        for (String name : bankNames) {
            if (!name.contentEquals(CommonUtils.ykb) && !name.contentEquals(CommonUtils.dnz) && !name.contentEquals(CommonUtils.isb)) {
                return false;
            }
        }
        return true;
    }

    public boolean isRequestSchemaValid(RankRequest request) {
        System.out.println(request.toString());

     return  true;
    }
}
