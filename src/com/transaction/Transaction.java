package com.transaction;

import java.time.LocalDateTime;

/**
 * @author Jayavani Swaminathan
 */
public class Transaction {
	
	String transactionId;
	String fromAccountId;
	String toAccountId;
	LocalDateTime createAt;
	double amount;
	String transactionType;
	String relatedTransaction;
	
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the fromAccountId
	 */
	public String getFromAccountId() {
		return fromAccountId;
	}
	/**
	 * @param fromAccountId the fromAccountId to set
	 */
	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	/**
	 * @return the toAccountId
	 */
	public String getToAccountId() {
		return toAccountId;
	}
	/**
	 * @param toAccountId the toAccountId to set
	 */
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	/**
	 * @return the createAt
	 */
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	/**
	 * @param createAt the createAt to set
	 */
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	/**
	 * @return the relatedTransaction
	 */
	public String getRelatedTransaction() {
		return relatedTransaction;
	}
	/**
	 * @param relatedTransaction the relatedTransaction to set
	 */
	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}
	
	/**
	 * 
	 * @param transactionId
	 * @param fromAccountId
	 * @param toAccountId
	 * @param createAt
	 * @param amount
	 * @param transactionType
	 * @param relatedTransaction
	 */
	public Transaction(String transactionId, String fromAccountId, String toAccountId, LocalDateTime createAt,
			double amount, String transactionType, String relatedTransaction) {
		super();
		this.transactionId = transactionId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.createAt = createAt;
		this.amount = amount;
		this.transactionType = transactionType;
		this.relatedTransaction = relatedTransaction;
	}
	
	
	
}
