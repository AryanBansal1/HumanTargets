package demo.HumanTargets.Serive;

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
}
