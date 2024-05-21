package than.project.bankapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import than.project.bankapp.dto.AccountDto;
import than.project.bankapp.service.AccountService;

@Controller
@RequestMapping("api/account")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping("/saveAccount")
	public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<AccountDto>> getAllAccounts(){
		
		List<AccountDto> accountDto = accountService.getAllAccounts();
		return ResponseEntity.ok(accountDto);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteAccount(@PathVariable Long id){

		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account is successfully deleted!");
	}
	
}
