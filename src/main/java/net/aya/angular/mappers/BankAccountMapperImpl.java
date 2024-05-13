package net.aya.angular.mappers;

import com.fasterxml.jackson.databind.util.BeanUtil;
import net.aya.angular.dtos.OperationDTO;
import net.aya.angular.dtos.SavingBankAccountDTO;
import net.aya.angular.dtos.currentBankAccountDTO;
import net.aya.angular.dtos.custumorDTO;
import net.aya.angular.entities.Custumor;
import net.aya.angular.entities.Operation;
import net.aya.angular.entities.SavingAccount;
import net.aya.angular.entities.currentAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {

    public custumorDTO fromCustumor(Custumor custumor) {
        custumorDTO custumorDTO1 = new custumorDTO();
//        custumorDTO1.setId(custumor.getId());
//        custumorDTO1.setName(custumor.getName());
//        custumorDTO1.setEmail(custumor.getEmail());
        //ou utilise copyproperties
        BeanUtils.copyProperties(custumor, custumorDTO1);

        return custumorDTO1;
    }

    public Custumor fromCustumorDto(custumorDTO custumorDTO) {
        Custumor custumor = new Custumor();
        BeanUtils.copyProperties(custumorDTO, custumor);
        return custumor;
    }
    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount){
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
        //on doit le faire manuellemnt car c ets un dto
        savingBankAccountDTO.setCustomerDTO(fromCustumor(savingAccount.getCustumor()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

  public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO){
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
        savingAccount.setCustumor(fromCustumorDto(savingBankAccountDTO.getCustomerDTO()));
        return savingAccount;
    }

    public currentBankAccountDTO fromCurrentBankAccount(currentAccount currentAccount){
        currentBankAccountDTO currentBankAccountDTO=new currentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustumor(currentAccount.getCustumor()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }

    public currentAccount fromCurrentBankAccountDTO(currentBankAccountDTO currentBankAccountDTO){
        currentAccount currentAccount=new currentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
        currentAccount.setCustumor(fromCustumorDto(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }


    public static OperationDTO fromAccountOperation(Operation accountOperation) {
        OperationDTO accountOperationDTO = new OperationDTO();
        BeanUtils.copyProperties(accountOperation, accountOperationDTO);
        return accountOperationDTO;
    }
    }

