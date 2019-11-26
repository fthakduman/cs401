package base.user.restcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import base.user.model.UserImpl;
import base.user.model.UserRole;
import base.user.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;

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

    @PutMapping("/user/{id}")
    public UserImpl updateUser(@RequestBody UserImpl userRecord, @PathVariable String id) {
        return userService.updateUser(id, userRecord);
    }

    @PutMapping("/user/resetpassword/{id}")
    public UserImpl updateUserPassword(@RequestBody UserImpl userRecord, @PathVariable String id) {
        return userService.updateUserPassword(id, userRecord);
    }

    @PutMapping("/user/changeRole/{id}")
    public UserImpl updateUserRole(@RequestBody UserImpl userRecord, @PathVariable String id) {
        return userService.updateUserRole(id, userRecord);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
        if (userService.findById(id) == null) {
            return "deleted";
        }
        return "delete operation failed";
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserImpl> getUserById(@PathVariable String id) {
        UserImpl userInfo = userService.findById(id);
        if (userInfo == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }


}