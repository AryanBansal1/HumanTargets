package demo.HumanTargets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.HumanTargets.Model.DonorForm;
import java.util.List;

@Repository
public interface Donor_form_repo extends JpaRepository<DonorForm,Long> {
    public List<DonorForm> findByUsername(String username);
}
