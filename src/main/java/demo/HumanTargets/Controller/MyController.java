package demo.HumanTargets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import demo.HumanTargets.Model.Donations;
import demo.HumanTargets.Model.DonorForm;
import demo.HumanTargets.Model.RequestGetter;
import demo.HumanTargets.Security.UserRepo;
import demo.HumanTargets.Security.Users;
import demo.HumanTargets.Serive.DonationService;
import demo.HumanTargets.Serive.DonorForm_service;
import demo.HumanTargets.Serive.GetterRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;








@Controller
public class MyController {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    
    @Autowired
    UserRepo userRepo;

    @Autowired
    GetterRequest getterRequest;
    
    @PostMapping("/NewdoneeRequest")
    public String NewdoneeRequest(@ModelAttribute RequestGetter requestDonee ) {
        System.out.println("reached here");
        getterRequest.newGetterRequest(requestDonee);
        return "";
    }

    @PostMapping("/register_process")
    public String register(@ModelAttribute Users users) {
        System.out.println("entered the registration process");
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
        donations.setDistrict(donorForm.getDistrict());
        donations.setCity(donorForm.getCity());
        donations.setDonatedby(donorForm.getUsername());
        if(donorForm.getYearold()==0){
            donations.setYearold("New");
        }
        else{
            donations.setYearold(String.valueOf(donorForm.getYearold()));
        }

        donationService.newDonation(donations);
        return "";
    }

    @GetMapping("/alldonations")
    @ResponseBody
    public List<Donations> getAlldonations(@RequestParam String distrcit) {
        return donationService.allitemsInCity(distrcit);
    }

    @GetMapping("/donationitems")
    public String donationitems() {
        return "/html/donatedItem.html";
    }
    
    

    @GetMapping("/mydonation")
    public String mydonations() {
        return "/html/mydonation.html";
    }

    @GetMapping("/myallDonations")
    @ResponseBody
    public List<DonorForm> myallDonations(@RequestParam String username) {
        return donorForm_service.mydonation(username);
    }


    @PutMapping("/book_donation_item")
    @ResponseBody
    public String book(@RequestParam Long id,@RequestParam String gettername) {
         donationService.updatestatus(id, gettername);
         return "Donation item booked successfully";
    }
    
    
    
    
    
    
}
