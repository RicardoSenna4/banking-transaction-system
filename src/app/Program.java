package app;

import java.util.Locale;
import java.util.Scanner;

import app.util.InputReader;
import banking.BankException;
import banking.BankService;
import banking.entities.Account;
import banking.entities.CheckingAccount;
import banking.entities.Client;
import banking.entities.SavingsAccount;
import banking.enums.ClientStatus;
import banking.BankException;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		BankService bankService = new BankService();
		InputReader input = new InputReader(sc);
		
		Integer menuChosenOption = 0;
		Boolean sair = false;
		
		while(sair == false) {
			
			UI.printMenu();
			
			menuChosenOption = input.readInt("Enter the desired option:");
			
			try {
				switch(menuChosenOption) {
					
					case 1: 
						//ação - register client
						System.out.println("==== REGISTER CLIENT OPERATION ====:");
						String holderName =  input.readNonBlankString("Enter the client name:");
						String cpf =  input.readNonBlankString("Enter the client CPF:");
						String email =  input.readNonBlankString("Enter the client e-mail:");
						ClientStatus status = ClientStatus.ACTIVE;
						
						Client client = new Client(holderName, cpf, email, status);
						bankService.registerClient(client);
						System.out.println("The register was succesfully done!\n\n");
						break;
						
					case 2: 
						//ação - Create checking account
						System.out.println("==== CREATE CHECKINGS ACCOUNT OPERATION ====:");
						String cpfToSearch = input.readNonBlankString("Enter the CPF:");
						Client searchClient = bankService.findClientByCpf(cpfToSearch);
						CheckingAccount checkingInfos = bankService.createCheckingAccount(searchClient);
						System.out.println(String.format("Sucess on creanting a new checking account\n"
								+ "The Checking account number is: %d\n\n", checkingInfos.getNumber()));
						break;	
						
					case 3: 
						//ação - Create savings account
						System.out.println("==== CREATE SAVINGS ACCOUNT OPERATION ====:");
						String cpfToSearching = input.readNonBlankString("Enter the CPF:");
						Client searchingClient = bankService.findClientByCpf(cpfToSearching);
						SavingsAccount savingsInfos = bankService.createSavingsAccount(searchingClient);
						System.out.println(String.format("Success on creating a new savings account\n"
								+ "The savings account number is: %d\n\n", savingsInfos.getNumber()));
						break;	
						
					case 4: 
						//ação - Deposit
						System.out.println("==== DEPOSIT OPERATION ====:");
						Integer number = input.readInt("Enter the account number:");
						Double amount = input.readDouble("Enter the amount to deposit:");
						bankService.deposit(number, amount);
						System.out.println(String.format("Sucess on the deposit operation\n"
								+ "The deposit on the account %d on the value of %.2f\n\n", number, amount));
					
						break;	
						
					case 5: 
						//ação - Withdraw
						System.out.println("==== WITHDRAW OPERATION ====:");
						Integer accountNumber = input.readInt("Enter the account number:");
						Double amountWithdraw = input.readDouble("Enter the amount to withdraw:");
						bankService.withdraw(accountNumber, amountWithdraw);
						System.out.println(String.format("Sucess on the withdraw operation\n"
								+ "The withdraw on the account %d on the value of %.2f\n\n", accountNumber, amountWithdraw));
					
						break;	
						
					case 6: 
						//ação - Transfer
						System.out.println("==== TRANSFER OPERATION ====:");
						Integer sourceAccountNumber = input.readInt("Enter the souce account number:");
						Integer targetAccountNumber = input.readInt("Enter the target account number:");
						Double amountTransfer = input.readDouble("Enter the amount to transfer:");
						
						bankService.transfer(sourceAccountNumber, targetAccountNumber, amountTransfer);
						System.out.println(String.format("Sucess on the transfer operation\n"
								+ "The amount of %.2f was transfered from the source account %d to the target account %d\n\n", 
								amountTransfer, sourceAccountNumber, targetAccountNumber));
				
						break;	
						
					case 7: 
						//ação - Account statement 
						System.out.println("==== STATEMENT OPERATION ====:");
						Integer statementAccountNumber = input.readInt("Enter the account number:");
						Account findAccount = bankService.findAccount(statementAccountNumber);
						UI.printAccountStatement(findAccount);
						System.out.println("\n\n");
						break;	
						
					case 8: 
						//ação - List accounts
						bankService.printAccount();
						System.out.println("\n\n");
						break;	
						
					case 9: 
						//ação - exit
						System.out.println("Exited the menu!");
						System.out.println("\n\n");
						sc.close();
						sair = true;
						break;	
						
					default:
				        System.out.println("Invalid option. Please choose a number between 1 and 9.\n\n");
				}
				
				}
			
					catch (BankException e) {
					    System.out.println(e.getMessage());
					}
			
		}
		
	}

}
