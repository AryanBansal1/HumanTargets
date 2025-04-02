package demo.HumanTargets.Serive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {

    @Autowired 
    private JavaMailSender javaMailSender;
    public void sendmail(String gettername,String donatedbyname,String itemName){
        // Email to getter
        SimpleMailMessage getterMessage = new SimpleMailMessage();
        getterMessage.setTo(gettername);
        getterMessage.setSubject("Item Booking Confirmation");
        getterMessage.setText("Dear Customer "  + ",\n\n" +
                "Your booking for the item '" + itemName + "' has been successfully confirmed.\n\n" +
                "Best regards,\nYour Team");
        javaMailSender.send(getterMessage);

         // Email to donor
         SimpleMailMessage donorMessage = new SimpleMailMessage();
         donorMessage.setTo(donatedbyname);
         donorMessage.setSubject("Your Item Has Been Booked");
         donorMessage.setText("Dear Donor " +   ",\n\n" +
                 "Your item '" + itemName + "' has been booked by " + gettername + ".\n\n" +
                 "Best regards,\nYour Team");
         javaMailSender.send(donorMessage);
    }
}
