package banking.entities;

import java.util.List;
import banking.enums.TransactionType;
import banking.enums.TransactionStatus;

import banking.BankException;
import banking.Transaction;
import banking.enums.AccountType;
import banking.enums.ClientStatus;

public class CheckingAccount extends Account{

	private Double withdrawFee;
	private AccountType accountType;
	
	//constructors
	public CheckingAccount() {
		
	}
	
	public CheckingAccount(Integer number, Client client, Double balance, Double withdrawFee, AccountType accountType) {
		super(number, client, balance);
		this.withdrawFee = withdrawFee;
		this.accountType = accountType;
	}	
	
	//getters; aqui setters não são necessários
	public Double getWithdrawFee() {
		return withdrawFee;
	}
	
	//methods
	public void deposit(Double amount) {
		super.deposit(amount); // chamando da superclasse e sem alterar o comportamento do código
	}
	
	@Override
	public void withdraw(Double amount) {
		if (amount <= 0.00) {
			throw new BankException("Error. The withdraw amount must be more than $0.00.");
		}
		
		if (getClient().getStatus() != ClientStatus.ACTIVE) {
			throw new BankException("Error. The client status is not activated.");
		}
		
		if ((amount + withdrawFee) > getBalance() || getBalance() <= 0.00) {
			throw new BankException("Error. The client have no available balance to the total withdraw.");
		}
		
		Double withdrawDiscount = getBalance() - amount - withdrawFee;
		setBalance(withdrawDiscount);

		Transaction newTransaction = new Transaction(
		        TransactionType.WITHDRAW,
		        TransactionStatus.APPROVED,
		        amount,
		        getNumber()
		);

		addTransaction(newTransaction);
	}
	
	public void transfer(Account target, Double amount) {
		super.transfer(target, amount); // chamamdo o metodo da superclasse sem fazer alteração no seu corpo
	}
	
	public void addTransaction(Transaction transaction) {
		super.addTransaction(transaction);
	}
	
	public List<Transaction> getStatement(){
		return super.getStatement();
	}
	
	@Override
	public  AccountType getAccountType() {
		return AccountType.CHECKING;
	};

}
