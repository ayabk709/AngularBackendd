package net.aya.angular;

import net.aya.angular.dtos.BankAccountDTO;
import net.aya.angular.dtos.SavingBankAccountDTO;
import net.aya.angular.dtos.currentBankAccountDTO;
import net.aya.angular.dtos.custumorDTO;
import net.aya.angular.entities.*;
import net.aya.angular.repisitories.AccountOperationRepisitory;
import net.aya.angular.repisitories.BankAccountrepisitory;
import net.aya.angular.repisitories.Custumorrepisitory;
import net.aya.angular.services.BankAccountNotFoundException;
import net.aya.angular.services.BankAccountService;
import net.aya.angular.services.CustumorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class AngularApplication implements CommandLineRunner {
@Autowired
public BankAccountrepisitory bankAccountrepisitory;
@Autowired
public AccountOperationRepisitory accountOperationRepisitory;
    @Autowired
public Custumorrepisitory custumorrepisitory;
    @Autowired
    BankAccountService bankAccountService;
    public static void main(String[] args) {
        SpringApplication.run(AngularApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        custumorDTO customer=new custumorDTO();
        customer.setName("ayo");
        customer.setEmail("ayoo"+"@gmail.com");
        bankAccountService.saveCustumor(customer);

       bankAccountService.listCustumors().forEach(c -> {
            try {
                bankAccountService.savecurrentBankAcocunt(Math.random()*90000, 500, c.getId());
                bankAccountService.saveSavingBankAcocunt(Math.random()*120000, 5.5, c.getId());
             List<BankAccountDTO> b=  bankAccountService.listBankAccounts();

             for(BankAccountDTO ba:b) {
                 for (int i = 0; i < 10; i++) {
                        String accountid;
                        if (ba instanceof currentBankAccountDTO) {
                            accountid = ((currentBankAccountDTO) ba).getId();
                        } else {
                            accountid = ((SavingBankAccountDTO) ba).getId();
                        }
                        bankAccountService.credit(accountid, Math.random() * 12000, "credit"
                                );
                        bankAccountService.debit(accountid, Math.random() * 12000, "debit"  );


                      }
             }

            } catch (CustumorNotFoundException e) {
                e.printStackTrace();
            }
            catch (BankAccountNotFoundException e) {
                e.printStackTrace();
            }

            });

       /* custumorrepisitory.findAll().forEach(c -> {
            //no need to instancier bankaccount car c ets une class inheritance
            currentAccount current = new currentAccount();
            current.setId(UUID.randomUUID().toString());
            current.setBalance(Math.random() * 9000);
            current.setCreationDate(new Date());
            current.setStatus(AccountStatus.CREATED);
            current.setCustumor(c);
            current.setOverdraft(9000);
            bankAccountrepisitory.save(current);

            SavingAccount savingAccount = new SavingAccount(); // Change variable name here
            savingAccount.setId(UUID.randomUUID().toString()); // Change variable name here
            savingAccount.setBalance(Math.random() * 9000); // Change variable name here
            savingAccount.setCreationDate(new Date()); // Change variable name here
            savingAccount.setStatus(AccountStatus.CREATED); // Change variable name here
            savingAccount.setCustumor(c); // Change variable name here
            savingAccount.setInterestRate(5.5); // Change variable name here
            bankAccountrepisitory.save(savingAccount); // Change variable name her
        });
//je cree des operations dnas un bankaccoutn 10 fois
        bankAccountrepisitory.findAll().forEach(acc -> {
            for (int i = 0; i < 10; i++) {
                Operation accountOperation = new Operation();
                accountOperation.setOperationDate(new Date());
                accountOperation.setAmount(Math.random() * 12000);
                accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                accountOperation.setBankAccount(acc);
                accountOperationRepisitory.save(accountOperation);
            }

        });*/
   /*     BankAccount ba =
                bankAccountrepisitory.findById("78accef3-1cf3-4fe0-9a99-dad94ef8d87b").orElse(null);
        System.out.println("*********************************");
        if (ba!=null) {
            System.out.println(ba.getId());
            System.out.println(ba.getOperations().size());
            if (ba instanceof currentAccount) {
                System.out.println(((currentAccount) ba).getOverdraft());
            } else if (ba instanceof SavingAccount) {
                System.out.println(((SavingAccount) ba).getInterestRate());
            }
            ba.getOperations().forEach(op -> {
                System.out.println(op.getAmount());
                System.out.println(op.getType());


            });
        }*/

    }
}
