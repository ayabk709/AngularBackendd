package net.aya.angular.dtos;

import lombok.Data;

@Data
public class DebitDTO {
    private String accountId;
    private double amount;
    private String description;
}