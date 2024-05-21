package than.project.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import than.project.bankapp.model.Account;

@Repository	
public interface AccountRepository extends JpaRepository<Account, Long>{

}
