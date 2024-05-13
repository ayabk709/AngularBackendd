package net.aya.angular.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aya.angular.dtos.custumorDTO;
import net.aya.angular.entities.Custumor;
import net.aya.angular.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
//toujours communicque avec couche service

public class CustumorRestController {
    private BankAccountService bankAccountService;

@GetMapping("/custumors")
    public List<custumorDTO> custumors(){
        return bankAccountService.listCustumors();
    }
//consulting custumor
@GetMapping("/custumors/{id}")
public custumorDTO getCustumor( @PathVariable(name="id") Long id) {
    return bankAccountService.getCustumor(id);


}
//ajout custumor
    //RequestBody pour dire que le body de la requete est un objet json
    @PostMapping("/custumors")
    public custumorDTO saveCustumor(@RequestBody custumorDTO c) {
return bankAccountService.saveCustumor(c);

    }
//update
    @PutMapping("/customers/{customerId}")
    public custumorDTO updateCustomer(@PathVariable Long customerId, @RequestBody custumorDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }
    //delete
    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        bankAccountService.deleteCustomer(customerId);
    }

}
