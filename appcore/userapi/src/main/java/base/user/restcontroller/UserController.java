package base.user.restcontroller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import base.user.model.UserImpl;
import base.user.model.UserRole;
import base.user.repository.UserRepository;
import base.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Users---------------------------------------------

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<UserImpl>> listAllUsers() {
        List<UserImpl> userImpls =  new ArrayList<UserImpl>();
        Iterator<UserImpl> iterator = userRepository.findAll().iterator();
        while (iterator.hasNext()){
            userImpls.add(iterator.next());
        }

        if (userImpls.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserImpl>>(userImpls, HttpStatus.OK);
    }
    @PostMapping("/registeruser")
    public UserImpl saveUser(@RequestBody UserImpl user) throws Exception {
        if(!userRepository.findUserImplByUserName(user.getUserName()).isEmpty()){
            throw new Exception("user exist");
        }
        else if ( !(user.getUserRole().contentEquals(UserRole.ADMIN_ROLE ) ||user.getUserRole().contentEquals(UserRole.PREMIUM_ROLE ) || user.getUserRole().contentEquals(UserRole.STANDART_ROLE )) ){
            throw new Exception("No such userRole");
        }
        else{
            return userRepository.save(user);
        }

    }
    @GetMapping("/getuser/{username}")
    public List<UserImpl> getUsers(@PathVariable String username){
        return userRepository.findUserImplByUserName(username);
    }


}