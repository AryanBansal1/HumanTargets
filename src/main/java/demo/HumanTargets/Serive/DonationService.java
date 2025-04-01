package demo.HumanTargets.Serive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.HumanTargets.Model.Donations;
import demo.HumanTargets.Repository.Donations_repo;
@Service
public class DonationService {
    @Autowired
    Donations_repo donations_repo;

    public void newDonation(Donations donations){
        donations_repo.save(donations);
    }

    public List<Donations> allitems(){
        return donations_repo.findAll();
    }

    public List<Donations> allitemsInCity(String city){
        return donations_repo.findByDistrictAndStatus(city, "Available");
    }

    public void updatestatus(Long id,String gettername){
        Donations don = donations_repo.findById(id).get();
        don.setStatus("Booked");
        don.setBookedby(gettername);
        donations_repo.save(don);
    }
}
