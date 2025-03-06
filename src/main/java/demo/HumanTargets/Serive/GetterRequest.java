package demo.HumanTargets.Serive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.HumanTargets.Model.RequestGetter;
import demo.HumanTargets.Repository.GetterRequest_repo;

@Service
public class GetterRequest {
    
    @Autowired
    GetterRequest_repo getterRequest_repo;

    public void newGetterRequest(RequestGetter requestGetter){
        getterRequest_repo.save(requestGetter);
    }
}
