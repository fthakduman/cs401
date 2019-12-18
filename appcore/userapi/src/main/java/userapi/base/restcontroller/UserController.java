package userapi.base.restcontroller;

import java.util.List;

import userapi.base.model.UserImpl;
import userapi.base.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserServiceImpl userService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Users---------------------------------------------


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<UserImpl>> listAllUsers() {
        List<UserImpl> userImpls = userService.findAllUsers();
        if (userImpls.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserImpl>>(userImpls, HttpStatus.OK);
    }

    @PostMapping("/registeruser")
    public UserImpl addUser(@RequestBody UserImpl userRecord) {

        return userService.saveUser(userRecord);

    }

    @PutMapping("/user/{username}")
    public UserImpl updateUser(@RequestBody UserImpl userRecord, @PathVariable String username) {
        return userService.updateUser(username, userRecord);
    }

    @PutMapping("/user/resetpassword/{username}")
    public UserImpl updateUserPassword(@RequestBody UserImpl userRecord, @PathVariable String username) {
        return userService.updateUserPassword(username, userRecord);
    }

    @PutMapping("/user/changeRole/{username}")
    public UserImpl updateUserRole(@RequestBody UserImpl userRecord, @PathVariable String username) {
        return userService.updateUserRole(username, userRecord);
    }

    @DeleteMapping("/user/{username}")
    public String deleteUser(@PathVariable String username) {
        userService.deleteUserById(username);
        if (userService.findById(username) == null) {
            return "deleted";
        }
        return "delete operation failed";
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserImpl> getUserById(@PathVariable String username) {
        UserImpl userInfo = userService.findById(username);
        if (userInfo == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }


}