package banking.entities;

import java.util.List;

import banking.Transaction;
import banking.enums.AccountType;

public class SavingsAccount extends Account{

	private Double interestRate = 0.01;
	private AccountType accountType;
	
	//constructors
	
	public SavingsAccount() {
		
		}
	
	public SavingsAccount(Integer number, Client client, Double balance, Double interestRate, AccountType accountType) {
		super(number, client, balance);	
		this.interestRate = interestRate;
		this.accountType = accountType;
	}

	//getters
	public Double getInterestRate() {
		return interestRate;
	}
	
	@Override
	public AccountType getAccountType() {
		return this.accountType = accountType.SAVINGS;
	}

	public void applyMonthlyInterest() {
		Double monthlyIncome = getBalance() * getInterestRate();
		this.setBalance(getBalance() + monthlyIncome);
	}
	
	//importar metodos da superclass
	
	public void deposit(Double amount) {
		super.deposit(amount);
	}
	
	public void withdraw(Double amount) {
		super.withdraw(amount); 
	}
	
	public void transfer(Account target, Double amount) {
		super.transfer(target, amount);
	}
	
	public void addTransaction(Transaction transaction) {
		super.addTransaction(transaction);
	}
	
	public List<Transaction> getStatement(){
		return super.getStatement();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
