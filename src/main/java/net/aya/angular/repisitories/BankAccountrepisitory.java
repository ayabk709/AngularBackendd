package net.aya.angular.repisitories;

import net.aya.angular.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountrepisitory extends JpaRepository<BankAccount, String> {
}
