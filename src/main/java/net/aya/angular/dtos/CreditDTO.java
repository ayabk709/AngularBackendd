package net.aya.angular.dtos;

import lombok.Data;

@Data
public class CreditDTO {
    private String accountId;
    private double amount;
    private String description;
}