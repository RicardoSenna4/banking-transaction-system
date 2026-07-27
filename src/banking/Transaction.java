package banking;

import java.util.Date;

import banking.enums.TransactionStatus;
import banking.enums.TransactionType;

public class Transaction {

	private Date moment;
	private TransactionType transactionType;
	private TransactionStatus transactionStatus;
	private Double amount;
	private Integer sourceAccountNumber;
	private Integer targetAccountNumber;
	
	//constructors
	public Transaction() {
		}
	//sobrecarga de construtor para casos de transações que não são transferências
	public Transaction(TransactionType transactionType, TransactionStatus transactionStatus, 
			Double amount, Integer sourceAccountNumber) {
		
			this.moment = new Date();
			this.transactionType = transactionType;
			this.transactionStatus = transactionStatus;
			this.amount = amount;
			this.sourceAccountNumber = sourceAccountNumber;
		}
	//sobrecarga de construtor para casos de transações que SÃO transferências entre contas
	public Transaction(TransactionType transactionType, TransactionStatus transactionStatus, 
			Double amount, Integer sourceAccountNumber, Integer targetAccountNumber) {
		
			this.moment = new Date();
			this.transactionType = transactionType;
			this.transactionStatus = transactionStatus;
			this.amount = amount;
			this.sourceAccountNumber = sourceAccountNumber;
			this.targetAccountNumber = targetAccountNumber;
		}
	
	//getters
	public Date getMoment() {
		return moment;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public Double getAmount() {
		return amount;
	}

	public Integer getSourceAccountNumber() {
		return sourceAccountNumber;
	}

	public Integer getTargetAccountNumber() {
		return targetAccountNumber;
	}
	
	//methods com os valores dos enums
	public void approve() {
		this.transactionStatus = TransactionStatus.APPROVED;
	}
	
	public void reject() {
		this.transactionStatus = TransactionStatus.REJECTED;
	}
	
	public void cancel() {
		this.transactionStatus = TransactionStatus.CANCELLED;
	}
	
	public void pending() {
		this.transactionStatus = TransactionStatus.PENDING;
	}
	

	@Override
	public String toString() {

	    if (targetAccountNumber == null) {
	        return String.format(
	            "%s - %s - %s - $%.2f - Account: %d",
	            moment,
	            transactionType,
	            transactionStatus,
	            amount,
	            sourceAccountNumber
	        );
	    }

	    return String.format(
	        "%s - %s - %s - $%.2f - From: %d To: %d",
	        moment,
	        transactionType,
	        transactionStatus,
	        amount,
	        sourceAccountNumber,
	        targetAccountNumber
	    );
	}
	
	}
	

