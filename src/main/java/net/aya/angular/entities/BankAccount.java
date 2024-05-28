package net.aya.angular.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Entity
//no need to instancier bankaccount car c ets une class inheritance
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//if we do per class we dont need to add DiscriminatorColumn or DiscriminatorValue in currentaccount
//creeation d une case type
@DiscriminatorColumn(name = "TYPE", length =4 )
@Data
@NoArgsConstructor
@AllArgsConstructor
//elle comme abstract
public class BankAccount {
    @Id

private String id;
private Date creationDate;
private double balance;
    @Enumerated(EnumType.STRING)
private AccountStatus status;
@ManyToOne
private Custumor custumor;
//parsque on a une relation bidirectionnelle
    //serialization
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
@OneToMany(mappedBy = "bankAccount",fetch = FetchType.EAGER)
private List<Operation> operations;






}
