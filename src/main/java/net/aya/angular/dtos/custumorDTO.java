package net.aya.angular.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.aya.angular.entities.BankAccount;

import java.util.List;

@Data

public class custumorDTO {
    private Long id;
    private String name;
    private String email;

}

