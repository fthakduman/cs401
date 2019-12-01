package userapi.base.config;

import userapi.base.model.ServiceUserDetails;
import userapi.base.model.UserImpl;
import userapi.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserImpl user = userService.findUserByUserName(username).iterator().next();

        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }


        return new ServiceUserDetails(user);
    }
}
