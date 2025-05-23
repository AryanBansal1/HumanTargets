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
import demo.HumanTargets.Serive.SimpleEmailService;

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
        System.out.println("You request for Seeker registration is successfully created, Wait for the admin approval"); 
        return "redirect:/login.html";
    }

    @PostMapping("/register_process")
    public String register(@ModelAttribute Users users) {
        System.out.println("entered the registration process");
        users.setPassword(encoder.encode(users.getPassword()));
        users.setRole("ROLE_Donor");
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
        System.out.println("Your item has been successfully register for donation");
        return "redirect:/register.html";
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

@GetMapping("/allD")
@ResponseBody
public List<DonorForm> alldonors() {
    return donorForm_service.allDonors();
}


@GetMapping("/allP")
@ResponseBody
public List<Donations> allproducts() {
    return donationService.allitems();
}

@Autowired
SimpleEmailService simpleEmailService;

@PostMapping("/contactus")
public String contactus(@RequestParam String name,@RequestParam String email,@RequestParam String subject,@RequestParam String message) {
        String body = String.format(
            "Name: %s\nEmail: %s\nSubject: %s\nMessage:\n%s",
            name, email, subject, message
            );
    simpleEmailService.contactusmail(body);
    return "redirect:/index.html";
}
    
}
