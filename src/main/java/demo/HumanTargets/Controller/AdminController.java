package demo.HumanTargets.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.HumanTargets.Model.Donations;
import demo.HumanTargets.Model.RequestGetter;
import demo.HumanTargets.Serive.DonationService;
import demo.HumanTargets.Serive.GetterRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
@RequestMapping("/Admin")
public class AdminController {
    
    @Autowired
    GetterRequest getterRequest;

    @Autowired
    DonationService donationService;

    @GetMapping("/")
    public String adminHome() {
        return "/admin.html";
    }
    

    @GetMapping("/all_getter_request")
    @ResponseBody
    public List<RequestGetter> Allreq() {
      return getterRequest.allGetterRequest();
      
    }

    @DeleteMapping("/reject_getter/{id}")
    @ResponseBody
    public Map<String,String> deleteGetter(@PathVariable Long id){
        getterRequest.deleteGetter(id);
        Map<String , String> response = new HashMap<>();
        response.put("message","Request denied Successfully");
        return response;
    }

    @PostMapping("/approve_getter/{id}")
    @ResponseBody
    public Map<String , String> approveGetter(@PathVariable Long id) {
        getterRequest.approveGetter(id);
        Map<String,String> response = new HashMap<>();
        response.put("message", "Request approved Successfully");
        return response;
    }

    @GetMapping("/booked-items")
    public String bookeditems(){
        System.out.println("Reached here");
        return "/all-booked-items.html";
    }

    @GetMapping("/all-booked-items")
    @ResponseBody
    public List<Donations> allitemsbooked() {
        return donationService.allbookeditems();
    }
    
    

    
}
