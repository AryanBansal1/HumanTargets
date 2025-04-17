package demo.HumanTargets.Serive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import demo.HumanTargets.Model.RequestGetter;
import demo.HumanTargets.Repository.GetterRequest_repo;
import demo.HumanTargets.Security.UserRepo;
import demo.HumanTargets.Security.Users;

@Service
public class GetterRequest {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    
    @Autowired
    GetterRequest_repo getterRequest_repo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    Users users;

    public void newGetterRequest(RequestGetter requestGetter){
        getterRequest_repo.save(requestGetter);
    }

    public List<RequestGetter> allGetterRequest(){
       return getterRequest_repo.findAll();
    }

    public void deleteGetter(Long id){
        RequestGetter getter = getterRequest_repo.findById(id).get();
        getterRequest_repo.delete(getter);
    }

    public void approveGetter(Long id){
        RequestGetter getter = getterRequest_repo.findById(id).get();
       users.setFullname(getter.getFullname());
       users.setUsername(getter.getUsername());
       users.setPassword(encoder.encode(getter.getPassword()));
       users.setPhonenumber(getter.getPhoneNumber());
       users.setRole("ROLE_Getter");
       userRepo.save(users);
        getterRequest_repo.delete(getter);
    }
}
