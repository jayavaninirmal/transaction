package com.transaction;

/**
 * @author Jayavani Swaminathan
 */
public class TransactionResult {

	double relativeBalance;
	int transactionCount;
	
	/**
	 * @return the relativeBalance
	 */
	public double getRelativeBalance() {
		return relativeBalance;
	}
	/**
	 * @param relativeBalance the relativeBalance to set
	 */
	public void setRelativeBalance(double relativeBalance) {
		this.relativeBalance = relativeBalance;
	}
	/**
	 * @return the transactionCount
	 */
	public int getTransactionCount() {
		return transactionCount;
	}
	/**
	 * @param transactionCount the transactionCount to set
	 */
	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}
	
}
