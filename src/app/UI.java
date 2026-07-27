package app;


import banking.BankException;
import banking.Transaction;
import banking.entities.Account;

public class UI {

	    public static void printMenu() {
	        System.out.println("BANKING SYSTEM");
	        System.out.println("1 - Register client");
	        System.out.println("2 - Create checking account");
	        System.out.println("3 - Create savings account");
	        System.out.println("4 - Deposit");
	        System.out.println("5 - Withdraw");
	        System.out.println("6 - Transfer");
	        System.out.println("7 - Account statement");
	        System.out.println("8 - List accounts");
	        System.out.println("9 - Exit");
	    }

	    public static void printError(String message) {
	        throw new BankException("Error: " + message);
	    }
	    
	    public static void printAccountStatement(Account account) {
	        if (account == null) {
	            System.out.println("Error. Account not found.");
	            return;
	            }
	        
	        System.out.println();
	        System.out.println("ACCOUNT STATEMENT");
	        System.out.println("-----------------------------");
	        System.out.println("Account number: " + account.getNumber());
	        System.out.println("Client: " + account.getClient().getName());
	        System.out.println("CPF: " + account.getClient().getCpf());
	        System.out.println("Email: " + account.getClient().getEmail());
	        System.out.println("Account type: " + account.getAccountType());
	        System.out.printf("Balance: $%.2f%n", account.getBalance());

	        System.out.println();
	        System.out.println("Transactions:");

	        if (account.getTransactionsInfos().isEmpty()) {
	            System.out.println("No transactions registered.");
	        } else {
	        	for (Transaction transaction : account.getStatement()) {
	        	    System.out.println(transaction);
	        	}
	        }
	    }
	    
	  
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}
	  
			
	    
	    
	    
	    
	    
	    
	    
	    
	

