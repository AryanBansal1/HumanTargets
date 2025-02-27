package demo.HumanTargets.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
@Component
public class MyUserDetailservice implements UserDetailsService{

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUsername(username);
        if(users==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(users);
    }
    
}
