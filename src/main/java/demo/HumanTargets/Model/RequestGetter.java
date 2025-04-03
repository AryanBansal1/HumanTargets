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
@Table(name = "Getter_request")
@Data
public class RequestGetter {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String username;
    private String password;
    private String fullname;
    private String phoneNumber;
    private String rationcardno;
    
}
