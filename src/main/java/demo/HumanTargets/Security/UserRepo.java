package demo.HumanTargets.Security;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<Users,Long> {
    public Users findByUsername(String username);
}
