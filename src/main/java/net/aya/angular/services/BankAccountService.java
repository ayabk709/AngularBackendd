package net.aya.angular.services;

import net.aya.angular.dtos.*;

import java.util.List;


public interface BankAccountService {
   custumorDTO saveCustumor(custumorDTO c);
   custumorDTO updateCustomer(custumorDTO c);
   void deleteCustomer(Long customerId);
    currentBankAccountDTO savecurrentBankAcocunt(double balance, double overdraft, Long custumorId);
    SavingBankAccountDTO saveSavingBankAcocunt(double balance, double interestRate, Long custumorId);
    List<custumorDTO> listCustumors();
    BankAccountDTO getBankAccount(String id) throws BankAccountNotFoundException;
    List<BankAccountDTO> listBankAccounts();

void debit(String accountID, double amount,String description) throws BankAccountNotFoundException;
void credit(String accountID, double amount,String description) throws BankAccountNotFoundException;
void transfer(String accountID1, String accountID2, double amount) throws BankAccountNotFoundException;
List<BankAccountDTO> banklist();

    custumorDTO getCustumor(Long id);

    /* @Override
         public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
             log.info("Saving new Customer");
             Customer customer=dtoMapper.fromCustomerDTO(customerDTO);
             Customer savedCustomer = customerRepository.save(customer);
             return dtoMapper.fromCustomer(savedCustomer);
         }*/
  /*  @Override
    public void deleteCustomer(Long customerId){
        custumorrepisitory.deleteById(customerId);
    }*/
    List<OperationDTO> accountHistory(String accountId);
    AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException;
}
