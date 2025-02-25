package demo.HumanTargets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.HumanTargets.Model.RequestDonee;
@Repository
public  interface Myrepo extends JpaRepository<RequestDonee,Long> {

}

