package demo.HumanTargets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.HumanTargets.Model.Donations;
import java.util.List;

@Repository
public interface Donations_repo extends JpaRepository<Donations,Long> {
    public List<Donations> findByDistrictAndStatus(String district,String status);
    
}
