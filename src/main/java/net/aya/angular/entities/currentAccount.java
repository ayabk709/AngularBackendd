package net.aya.angular.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@DiscriminatorValue("CA")
@NoArgsConstructor
public class currentAccount extends BankAccount{
    private double overdraft;


}
