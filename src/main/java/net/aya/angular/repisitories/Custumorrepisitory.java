package net.aya.angular.repisitories;

import net.aya.angular.entities.BankAccount;
import net.aya.angular.entities.Custumor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Custumorrepisitory  extends JpaRepository<Custumor, Long> {
    //search
    @Query("select c from Custumor c where c.name like :kw")
    List<Custumor> Search(@Param("kw") String keyword);
    //public List<Custumor> findByNameContains(String keyword);
}
