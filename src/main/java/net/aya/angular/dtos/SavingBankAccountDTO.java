package net.aya.angular.dtos;

import lombok.Data;
import net.aya.angular.entities.AccountStatus;

import java.util.Date;

@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private custumorDTO customerDTO;
    private double interestRate;
}