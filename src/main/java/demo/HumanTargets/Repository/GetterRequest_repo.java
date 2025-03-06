package demo.HumanTargets.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import demo.HumanTargets.Model.RequestGetter;
@Repository
public  interface GetterRequest_repo extends JpaRepository<RequestGetter,Long> {

}

