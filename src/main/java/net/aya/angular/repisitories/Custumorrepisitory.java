package net.aya.angular.repisitories;

import net.aya.angular.entities.BankAccount;
import net.aya.angular.entities.Custumor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Custumorrepisitory  extends JpaRepository<Custumor, Long> {
}
