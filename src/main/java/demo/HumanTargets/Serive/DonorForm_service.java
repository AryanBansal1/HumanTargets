package demo.HumanTargets.Serive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.HumanTargets.Model.DonorForm;
import demo.HumanTargets.Repository.Donor_form_repo;

@Service
public class DonorForm_service {
    @Autowired
    Donor_form_repo donor_form_repo;

    public void save(DonorForm donorForm){
        donor_form_repo.save(donorForm);
    }   

}
