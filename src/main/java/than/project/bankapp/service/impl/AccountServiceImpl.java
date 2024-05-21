package than.project.bankapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import than.project.bankapp.dto.AccountDto;
import than.project.bankapp.mapper.AccountMapper;
import than.project.bankapp.model.Account;
import than.project.bankapp.repository.AccountRepository;
import than.project.bankapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	
	
	private AccountRepository accountRepo;
	

	public AccountServiceImpl(AccountRepository accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}



	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account saveAccount = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}



	@Override
	public AccountDto getAccountById(Long id) {
		
		Account account = accountRepo
				.findById(id).
				orElseThrow(()-> new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}



	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepo
				.findById(id).
				orElseThrow(()-> new RuntimeException("Account does not exist"));
		
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepo
				.findById(id).
				orElseThrow(()-> new RuntimeException("Account does not exist"));
		
		double balance = account.getBalance();
		
		if(balance < amount){
			throw new RuntimeException("Insufficient Balance");
		}
		
		double total = balance - amount;
		
		account.setBalance(total);
		Account savedAccount = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}



	@Override
	public List<AccountDto> getAllAccounts() {
		
		List<Account> accounts = accountRepo.findAll();
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	
	}



	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepo.findById(id).
				orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		accountRepo.deleteById(id);
		
	}




}
