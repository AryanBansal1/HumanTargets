package demo.HumanTargets.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseBody
    public String NewdoneeRequest(@ModelAttribute RequestGetter requestDonee ) {
        System.out.println("reached here");
        getterRequest.newGetterRequest(requestDonee);
        return "You request for Seeker registration is successfully created, Wait for the admin approval";
    }

    @PostMapping("/register_process")
    public String register(@ModelAttribute Users users) {
        System.out.println("entered the registration process");
        users.setPassword(encoder.encode(users.getPassword()));
        users.setRole("Donor");
        userRepo.save(users);
        return "/login.html";
    }

    @GetMapping("/")
    public String home() {
        return "/index.html";
    }
    @Autowired
    Donations donations;

    @Autowired
    DonationService donationService;

    @Autowired
    DonorForm_service donorForm_service;

    @PostMapping("/donor_form")
    @ResponseBody
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
        return "Your item has been successfully register for donation";
    }

    @GetMapping("/alldonations")
    @ResponseBody
    public List<Donations> getAlldonations(@RequestParam String distrcit) {
        return donationService.allitemsInCity(distrcit);
    }

    @GetMapping("/donationitems")
    public String donationitems() {
        return "/donatedItem.html";
    }
    
    

    @GetMapping("/mydonation")
    public String mydonations() {
        return "/mydonation.html";
    }

    @GetMapping("/myallDonations")
    @ResponseBody
    public List<Donations> myallDonations(@RequestParam String username) {
        return donationService.listOFdonatedby(username);
    }


@PutMapping("/book_donation_item")
@ResponseBody
public ResponseEntity<Map<String, String>> book(@RequestParam Long id, @RequestParam String gettername) {
    Map<String, String> response = new HashMap<>();
    try {
        donationService.updatestatus(id, gettername);
        response.put("message", "Donation item booked successfully");
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        response.put("message", "Error booking donation item: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}



    
    
    
    
    
    
    
}
