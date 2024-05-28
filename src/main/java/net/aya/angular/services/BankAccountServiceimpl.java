package net.aya.angular.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aya.angular.dtos.*;
import net.aya.angular.entities.*;
import net.aya.angular.mappers.BankAccountMapperImpl;
import net.aya.angular.repisitories.AccountOperationRepisitory;
import net.aya.angular.repisitories.BankAccountrepisitory;
import net.aya.angular.repisitories.Custumorrepisitory;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service

@Transactional //spring
@AllArgsConstructor
public class BankAccountServiceimpl  implements BankAccountService {


    //  @Autowired
    private Custumorrepisitory custumorrepisitory;
    //@Autowired
    private BankAccountrepisitory bankAccountrepisitory;
    //@Autowired
    private AccountOperationRepisitory accountOperationRepisitory;
//pour loger des message
    // Logger logger= LoggerFactory.getLogger(this.getClass().getName());
//log.info(")

    private BankAccountMapperImpl bankAccountMapper;


    @Override
    public custumorDTO saveCustumor(custumorDTO c) {
        log.info("saving custumor");
        // convert the custumorDTO object c to a Custumor object.
        Custumor customer = bankAccountMapper.fromCustumorDto(c);
        //convert dto then save
        Custumor savedCustomer = custumorrepisitory.save(customer);
        //then converted to dto
        return bankAccountMapper.fromCustumor(savedCustomer);
    }

    @Override
    public custumorDTO updateCustomer(custumorDTO customerDTO) {
        log.info("Saving new Customer");
        Custumor customer = bankAccountMapper.fromCustumorDto(customerDTO);
        Custumor savedCustomer = custumorrepisitory.save(customer);
        return bankAccountMapper.fromCustumor(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        custumorrepisitory.deleteById(customerId);
    }

    @Override
    public currentBankAccountDTO savecurrentBankAcocunt(double balance, double overdraft, Long custumorId) {
        Custumor custumor = custumorrepisitory.findById(custumorId).orElse(null);
        if (custumor == null)
            throw new CustumorNotFoundException("Custumor not found");

        currentAccount bankAccount = new currentAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(balance);
        bankAccount.setCreationDate(new Date());
        bankAccount.setOverdraft(overdraft);
        bankAccount.setCustumor(custumor);
        currentAccount F = bankAccountrepisitory.save(bankAccount);
//ici on fait le save d un crruent dto et on le convertit en dto a partir de class mapper
        return bankAccountMapper.fromCurrentBankAccount(F);
    }


    @Override
    public SavingBankAccountDTO saveSavingBankAcocunt(double balance, double interestRate, Long custumorId) {
        Custumor custumor = custumorrepisitory.findById(custumorId).orElse(null);
        if (custumor == null)
            throw new CustumorNotFoundException("Custumor not found");

        SavingAccount bankAccount = new SavingAccount();
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setBalance(balance);
        bankAccount.setCreationDate(new Date());
        bankAccount.setInterestRate(interestRate);
        bankAccount.setCustumor(custumor);
        SavingAccount F = bankAccountrepisitory.save(bankAccount);

        return bankAccountMapper.fromSavingBankAccount(F);

    }


    @Override
    public List<custumorDTO> listCustumors() {
        List<Custumor> custumors = custumorrepisitory.findAll();
        List<custumorDTO> custumorDTOS = custumors.stream().map(custumor -> bankAccountMapper.fromCustumor(custumor)).collect(Collectors.toList());
        //j ai transfere une liste de custumor en une liste de dto
     /*   List<custumorDTO> custumorDTOS = new ArrayList<>();
        for (Custumor custumor : custumors) {
            custumorDTO custumorDTO = bankAccountMapper.fromCustumor(custumor);
            custumorDTOS.add(custumorDTO);
        }*/
        return custumorDTOS;

    }

    @Override
    public List<custumorDTO> Search(String keyword) {
  List<Custumor> custumors=custumorrepisitory.Search("%"+keyword+"%");

          List<custumorDTO> custumorodtos= custumors.stream().map(custumor -> bankAccountMapper.fromCustumor(custumor)).collect(Collectors.toList());
        return custumorodtos;

    }

    @Override
    public BankAccountDTO getBankAccount(String id) throws BankAccountNotFoundException {
        BankAccount b = bankAccountrepisitory.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
        if (b instanceof SavingAccount)
            return bankAccountMapper.fromSavingBankAccount((SavingAccount) b);
        else
            return bankAccountMapper.fromCurrentBankAccount((currentAccount) b);
    }


    @Override
    public List<BankAccountDTO> listBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountrepisitory.findAll();
        return bankAccounts.stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount)
                return bankAccountMapper.fromSavingBankAccount((SavingAccount) bankAccount);
            else
                return bankAccountMapper.fromCurrentBankAccount((currentAccount) bankAccount);
        }).collect(Collectors.toList());
    }

    @Override
    public void debit(String accountID, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountrepisitory.findById(accountID)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));

        if (bankAccount.getBalance() < amount)
            throw new BankAccountNotFoundException("Insufficient balance");
        Operation accountOperation = new Operation();
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setDescription(description);
        accountOperationRepisitory.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        bankAccountrepisitory.save(bankAccount);


    }

    @Override
    public void credit(String accountID, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountrepisitory.findById(accountID)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
        Operation accountOperation = new Operation();
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setDescription(description);
        accountOperationRepisitory.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        bankAccountrepisitory.save(bankAccount);
    }

    @Override
    public void transfer(String accountID1, String accountID2, double amount) throws BankAccountNotFoundException {
        debit(accountID1, amount, "transfer to " + accountID2);
        credit(accountID2, amount, "transfer from " + accountID1);

    }

    @Override
    public List<BankAccountDTO> banklist() {
        //the method needed to convert each BankAccount
        // object to its corresponding DTO representation (BankAccountDTO) because the method signature now indicates that it should return a list of DTOs
        List<BankAccount> bankAccounts = bankAccountrepisitory.findAll();
        return bankAccounts.stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount)
                return bankAccountMapper.fromSavingBankAccount((SavingAccount) bankAccount);
            else
                return bankAccountMapper.fromCurrentBankAccount((currentAccount) bankAccount);
        }).collect(Collectors.toList());
    }

    @Override
    public custumorDTO getCustumor(Long id) throws CustumorNotFoundException {
        Custumor customer = custumorrepisitory.findById(id)
                .orElseThrow(() -> new CustumorNotFoundException("Customer Not found"));
        return bankAccountMapper.fromCustumor(customer);
    }

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
    @Override
    public List<OperationDTO> accountHistory(String accountId) {
        List<Operation> accountOperations = accountOperationRepisitory.findByBankAccountId(accountId);
        return accountOperations.stream().map(op -> BankAccountMapperImpl.fromAccountOperation(op)).collect(Collectors.toList());
    }


    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
        BankAccount bankAccount=bankAccountrepisitory.findById(accountId).orElse(null);
        if(bankAccount==null) throw new BankAccountNotFoundException("Account not Found");
        Page<Operation> accountOperations = accountOperationRepisitory.findByBankAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO=new AccountHistoryDTO();
        List<OperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> bankAccountMapper.fromAccountOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }


   /* @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers=customerRepository.searchCustomer(keyword);
        List<CustomerDTO> customerDTOS = customers.stream().map(cust -> dtoMapper.fromCustomer(cust)).collect(Collectors.toList());
        return customerDTOS;
    }*/
}