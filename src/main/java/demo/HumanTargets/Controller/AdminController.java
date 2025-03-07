package demo.HumanTargets.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.HumanTargets.Model.RequestGetter;
import demo.HumanTargets.Serive.GetterRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
@RequestMapping("/Admin")
public class AdminController {
    
    @Autowired
    GetterRequest getterRequest;

    @GetMapping("/")
    public String adminHome() {
        return "/html/allGetter.html";
    }
    

    @GetMapping("/all_getter_request")
    @ResponseBody
    public List<RequestGetter> Allreq() {
      return getterRequest.allGetterRequest();
      
    }

    @DeleteMapping("/reject_getter/{id}")
    @ResponseBody
    public void deleteGetter(@PathVariable Long id){
        getterRequest.deleteGetter(id);
    }

    @PostMapping("/approve_getter/{id}")
    @ResponseBody
    public void approveGetter(@PathVariable Long id) {
        getterRequest.approveGetter(id);
    }
    

    
}
