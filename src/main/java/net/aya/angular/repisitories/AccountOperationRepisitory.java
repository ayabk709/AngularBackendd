package net.aya.angular.repisitories;

import net.aya.angular.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepisitory extends JpaRepository<Operation, Long> {


// Since you have a ManyToOne association between Operation and BankAccount, you can directly query by the BankAccount entity in the Operation entity.
    List<Operation> findByBankAccountId(String accountId);
    Page<Operation> findByBankAccountIdOrderByOperationDateDesc(String accountId, Pageable pageable);
}
