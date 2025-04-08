package demo.HumanTargets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import demo.HumanTargets.Model.DonorForm;
import java.util.List;

@Repository
public interface Donor_form_repo extends JpaRepository<DonorForm,Long> {
    public List<DonorForm> findByUsername(String username);

    @Query(value = """
    SELECT * FROM (
        SELECT *, ROW_NUMBER() OVER (PARTITION BY username ORDER BY id) AS rn
        FROM donor_donation_form
    ) sub
    WHERE rn = 1
""", nativeQuery = true)
    public List<DonorForm> findDistinctByUsername();
}
