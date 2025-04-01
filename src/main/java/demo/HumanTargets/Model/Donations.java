package demo.HumanTargets.Model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Component
@Data
@Entity
@Table(name = "Donation_items")
public class Donations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;    // Shoes, Stationery , Bags, clothes, furniture
    private String item_name;
    private String yearold;
    private String district;
    private String city;
    private String status;
    private String bookedby;

    @PrePersist
    public void setDefault(){
        if(this.status==null || this.status.isEmpty()){
            this.status="Available";
        }
    }

}
