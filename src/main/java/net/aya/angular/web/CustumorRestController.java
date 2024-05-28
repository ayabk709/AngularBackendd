package net.aya.angular.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.aya.angular.dtos.custumorDTO;
import net.aya.angular.entities.Custumor;
import net.aya.angular.services.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j

//toujours communicque avec couche service
//allow cross-origin requests from any origin.
//in securirty it wont work
//@CrossOrigin("*")
public class CustumorRestController {
    private BankAccountService bankAccountService;

@GetMapping("/custumors")
@PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<custumorDTO> custumors(){
        return bankAccountService.listCustumors();
    }
    @GetMapping("/custumors/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<custumorDTO> custumors(@RequestParam(name="keyword",defaultValue = "") String keyword){
        return bankAccountService.Search(keyword);
    }
//consulting custumor
@GetMapping("/custumors/{id}")
@PreAuthorize("hasAuthority('SCOPE_USER')")
public custumorDTO getCustumor( @PathVariable(name="id") Long id) {
    return bankAccountService.getCustumor(id);


}
//ajout custumor
    //RequestBody pour dire que le body de la requete est un objet json
    @PostMapping("/custumors")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public custumorDTO saveCustumor(@RequestBody custumorDTO c) {
return bankAccountService.saveCustumor(c);

    }
//update
    @PutMapping("/customers/{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")

    public custumorDTO updateCustomer(@PathVariable Long customerId, @RequestBody custumorDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }
    //delete
    @DeleteMapping("/customers/{customerId}")
   @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public void deleteCustomer(@PathVariable Long customerId){
        bankAccountService.deleteCustomer(customerId);
    }

}
