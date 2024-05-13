package net.aya.angular.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aya.angular.entities.BankAccount;
import net.aya.angular.entities.OperationType;

import java.util.Date;


@Data

public class OperationDTO {


    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType Type;
    private String description;

}
