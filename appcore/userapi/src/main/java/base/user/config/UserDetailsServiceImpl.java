package base.user.config;

import base.user.model.ServiceUserDetails;
import base.user.model.UserImpl;
import base.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
