package demo.HumanTargets.Model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Component
@Entity
@Data
@Table(name = "Donor_donation_form")
public class DonorForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String item_name;
    private String username;
    private String mobile;
    private String panCard;
    private int yearold;
    private String category;
    private String city;



}
