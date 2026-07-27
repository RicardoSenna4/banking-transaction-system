package banking;

import java.util.ArrayList;
import java.util.List;

import banking.entities.Account;
import banking.entities.CheckingAccount;
import banking.entities.Client;
import banking.entities.SavingsAccount;
import banking.enums.AccountType;
import banking.enums.ClientStatus;
import java.util.Random;//para trabalhar com valores aleatórios


public class BankService {

	private List<Client> clients = new ArrayList<>();
	private List<Account> accounts = new ArrayList<>();;
	private Integer nextAccountNumber; //vai ser gerado auttomaticamente

	//não coloquei construtor vazio para evitar erros de inicialização vazia 
	
	//construtores 
	public BankService() {
		Random random = new Random();//class Random para usar valores aleatórios
		// Gera um número de 1000 a 9999 para garantir que sempre tenha 4 dígitos
		this.nextAccountNumber = 1000 + random.nextInt(9000);
	}

	//getters
	public List<Client> getClients() {
		return clients;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public Integer getNextAccountNumber() {
		return nextAccountNumber;
	}
	
	//methods	
	
	public void registerClient(Client client) {
		
		if (client == null) {
			throw new BankException("Error. The client informations is not available.");
		}
		
		//o forEach() esta varrendo todos os elementos c da List clients
		for (Client c : clients) {
			if(c.getCpf().equals(client.getCpf())){
				throw new BankException("Error. The CPF is currently in use.");
			}
		}
		
		clients.add(client);
		
	}
	
	
	public Account findAccount(Integer number) {
		for (Account acc : accounts) {
			if(acc.getNumber().equals(number)) {
				return acc;
			}
			
		}
		throw new BankException("Error. Account not found.");
	}
	
	
	public Client findClientByCpf(String cpf) {
		for (Client c : clients) {
			if(c.getCpf().equals(cpf)) {
				return c;
			}
		}
		throw new BankException("Error. Client not found.");
	}
	
	
	public CheckingAccount createCheckingAccount(Client client) {
		//o cliente DEVE estar cadastrado na minha List de clients para poder ser instanciado em uma class CheckingAccount
		if(!(clients.contains(client))) {
				throw new BankException("Error. The client are not registeres in the Database.");
				}
		
		if(client.getStatus() != ClientStatus.ACTIVE) {
			throw new BankException("Error. The client status is not active.");
		}
		
		//ArrayLists encadeados - varrendo elementos de 2 arrays e comparando o atributo cpf de cada um dos elementos
		//conferindo se ja temos clientes com esse cpf cadastrados 
			for(Account acc : accounts) {
				if((client.getCpf().equals(acc.getClient().getCpf())) && acc.getAccountType() == AccountType.CHECKING) {
					throw new BankException("Error. The CPF is currently in use on a checking account.");
				}
			}
		
		
		//instanciar a class CheckingAccount
		CheckingAccount newCheckingAccount = new CheckingAccount(nextAccountNumber, client, 0.00, 5.0, AccountType.CHECKING);
		nextAccountNumber++;
		//add na List
		accounts.add(newCheckingAccount);
		
		return newCheckingAccount;
		
	}
	
	
	public SavingsAccount createSavingsAccount(Client client) {
		
		//o cliente DEVE estar cadastrado na minha List de clients para poder ser instanciado em uma class SavingsAccount
		if(!(clients.contains(client))) {
			throw new BankException("Error. The client are not registeres in the Database.");
			}
		
		if(client.getStatus() != ClientStatus.ACTIVE) {
			throw new BankException("Error. The client status is not active.");
		}
		
		//ArrayLists encadeados - varrendo elementos de 2 arrays e comparando o atributo cpf de cada um dos elementos
		//conferindo se ja temos clientes com esse cpf cadastrados 
			for(Account acc : accounts) {
				if((client.getCpf().equals(acc.getClient().getCpf())) && acc.getAccountType() == AccountType.SAVINGS) {
					throw new BankException("Error. The CPF is currently in use on a savings account.");
				}
			}
		
		
		//instanciar a class CheckingAccount
		SavingsAccount newSavingsAccount = new SavingsAccount(nextAccountNumber, client, 0.00, 0.05, AccountType.SAVINGS);
		nextAccountNumber++;
		//add na List
		accounts.add(newSavingsAccount);
		
		return newSavingsAccount;
	}		
	
	
	public void deposit(Integer accountNumber, Double amount) {
		
		Account accountInfos = this.findAccount(accountNumber);
		accountInfos.deposit(amount);
	}
	
	public void withdraw(Integer accountNumber, Double amount) {
		
		Account accountInfos = this.findAccount(accountNumber);
		accountInfos.withdraw(amount); // polimorfismo - o sistema vai procurar o numero da account e ver se ela é savings ou checking e vai usar								// o metodo ideal
	}
	
	
	public void transfer(Integer sourceNumber, Integer targetAccount, Double amount) {
		Account sourceAccountInfos = this.findAccount(sourceNumber);
		Account targetAccountInfos = this.findAccount(targetAccount);
		
		sourceAccountInfos.transfer(targetAccountInfos, amount);
	}
	
	
	//imprimir os objetos da List de contas cadastradas
	public List<Account> printAccount(){
			System.out.println(String.format("Contas cadastradas:\n"));
		for(Account la : accounts) {
			System.out.println(la);
		}
		return accounts;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	

