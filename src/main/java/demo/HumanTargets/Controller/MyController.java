package demo.HumanTargets.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import demo.HumanTargets.Model.Donations;
import demo.HumanTargets.Model.DonorForm;
import demo.HumanTargets.Model.RequestGetter;
import demo.HumanTargets.Security.UserRepo;
import demo.HumanTargets.Security.Users;
import demo.HumanTargets.Serive.DonationService;
import demo.HumanTargets.Serive.DonorForm_service;
import demo.HumanTargets.Serive.GetterRequest;

import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class MyController {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    GetterRequest getterRequest;
    
    @PostMapping("/NewdoneeRequest")
    public String NewdoneeRequest(@ModelAttribute RequestGetter requestDonee ) {
        getterRequest.newGetterRequest(requestDonee);
        return "";
    }

    @PostMapping("/register_process")
    public String register(@ModelAttribute Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        users.setRole("Donor");
        userRepo.save(users);
        return "";
    }

    @GetMapping("/")
    public String home() {
        return "/html/index.html";
    }
    @Autowired
    Donations donations;

    @Autowired
    DonationService donationService;

    @Autowired
    DonorForm_service donorForm_service;

    @PostMapping("/donor_form")
    public String donation(@ModelAttribute DonorForm donorForm) {
        donorForm_service.save(donorForm);
        donations.setItem_name(donorForm.getItem_name());
        donations.setCategory(donorForm.getCategory());
        if(donorForm.getYearold()==0){
            donations.setYearold("New");
        }
        else{
            donations.setYearold(String.valueOf(donorForm.getYearold()));
        }

        donationService.newDonation(donations);
        return "";
    }
    
    
    
}
