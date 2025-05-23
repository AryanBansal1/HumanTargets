package demo.HumanTargets.Security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                if(authentication.getAuthorities().toString().contains("ROLE_Admin")){
                    response.sendRedirect("/Admin/");
                }
                else if(authentication.getAuthorities().toString().contains("Donor")){
                    response.sendRedirect("/mydonation");
                }
                else{
                    response.sendRedirect("/donationitems");
                }
    }
    
}
