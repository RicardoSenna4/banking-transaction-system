package banking.entities;

import java.util.ArrayList;
import java.util.List;

import banking.BankException;
import banking.Transaction;
import banking.enums.AccountType;
import banking.enums.ClientStatus;
import banking.enums.TransactionType;
import banking.enums.TransactionStatus;

public abstract class Account {

	private Integer number;
	private Client client;
	private Double balance;
	private Transaction transaction;
	private List <Transaction> transactionsInfos = new ArrayList<>();
	
	//constructors
	public Account() {
		
	}

	public Account(Integer number, Client client, Double balance) {
		super();
		this.number = number;
		this.client = client;
		this.balance = balance;
	}

	//getters
	//maior parte dos setters n serão necessários
	public Integer getNumber() {
		return number;
	}

	public Client getClient() {
		return client;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;	
	}
	
	public Transaction getTransaction() {
		return transaction;
	}
	
	public List<Transaction> getTransactionsInfos() {
		return transactionsInfos;
	}
	
	//methods
	public void deposit(Double amount) {
		if (amount <= 0.00) {
			throw new BankException("Error. The deposit amount must be more than $0.00.");
		}
		
		if (client.getStatus() != ClientStatus.ACTIVE) {
			throw new BankException("Error. The client status is not activated.");
		}
		
		balance += amount;

		Transaction newTransaction = new Transaction(
			    TransactionType.DEPOSIT,
			    TransactionStatus.APPROVED,
			    amount,
			    getNumber()
			);
		
		addTransaction(newTransaction);
	}
	
	public void withdraw(Double amount) {
		if (amount <= 0.00) {
			throw new BankException("Error. The withdraw amount must be more than $0.00.");
		}
		
		if (client.getStatus() != ClientStatus.ACTIVE) {
			throw new BankException("Error. The client status is not activated.");
		}
		
		if (amount > getBalance() || getBalance() <= 0.00) {
			throw new BankException("Error. The client have no available balance to the total withdraw.");
		}
		
		balance -= amount;
		Transaction newTransaction = new Transaction(
			    TransactionType.WITHDRAW,
			    TransactionStatus.APPROVED,
			    amount,
			    getNumber()
			);
		
		addTransaction(newTransaction);
	}
	
	public void transfer(Account target, Double amount) {
		
		if (amount <= 0.00) {
			throw new BankException("Error. The transfer amount must be more than $0.00.");
		}
		//verificando se o usuario de origem esta ativo
		if (client.getStatus() != ClientStatus.ACTIVE) {
			throw new BankException("Error. The client status of the origin account is not Active.");
		}
		//verificando se a conta de destino existe
		if (target.getNumber().equals(null) ) {
			throw new BankException("Error. The target Account does not exists.");
		}
		
		//verificando se o usuário de destino esta ativo
		if (target.getClient().getStatus() != ClientStatus.ACTIVE) {
			throw new BankException("Error. The client status of the target account is not Active.");
		}
		//verificando se a conta de origem e destino são diferentes 
		if (target.getNumber().equals(getNumber()) ) {
			throw new BankException("Error. The target Account and the origin Account are both the same.");
		}
		
		//verificando se ha saldo na conta 
		if (amount > getBalance() || getBalance() <= 0.00) {
			throw new BankException("Error. The balance account is not enough for the operation.");
		}

		this.setBalance(getBalance() - amount);
		target.setBalance(target.getBalance() + amount);
		
		Transaction transferOut = new Transaction(
		        TransactionType.TRANSFER_OUT,
		        TransactionStatus.APPROVED,
		        amount,
		        getNumber(),
		        target.getNumber()
		);

		Transaction transferIn = new Transaction(
		        TransactionType.TRANSFER_IN,
		        TransactionStatus.APPROVED,
		        amount,
		        getNumber(),
		        target.getNumber()
		);

		addTransaction(transferOut);
		target.addTransaction(transferIn);
		}
	
	//metodo para adicionar nova transação na List. A transação é do tipo Transaction e é inicializada nos metodos individuais
	public void addTransaction(Transaction newTransaction) {
		transactionsInfos.add(newTransaction);
	}
	
	//metodo para imprimir o histórico da conta/dados salvos na list na ordem correta
	public List<Transaction> getStatement(){
			return transactionsInfos;
	}
	
	//escopo de metodo para ser feito Override nas subclasses
	public abstract AccountType getAccountType();
	
	@Override
	public String toString() {
	    return String.format(
	        "Account number: %d | Client: %s | CPF: %s | Type: %s | Balance: $%.2f",
	        getNumber(),
	        getClient().getName(),
	        getClient().getCpf(),
	        getAccountType(),
	        getBalance()
	    );
	}
	
	
	
	
	
	
	
	
	
}
