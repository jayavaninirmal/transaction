package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.transaction.Transaction;
import com.transaction.TransactionResult;
import com.transaction.util.PersistenceHelper;
import com.transaction.util.TransactionService;

/**
 * @author Jayavani Swaminathan
 */
public class TransactionTest {

	@Test
	public void testDatabaseConnection() {
		Connection conn=PersistenceHelper.getConnection();
		assertNotNull("Verify connection is NOT null", conn);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void calculateRelativeBalance() {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
	    LocalDateTime createAt = LocalDateTime.parse("20/10/2018 12:47:35", formatter);
	    
		Transaction transaction = new Transaction("TX10001", "ACC334455", "ACC778899", createAt, 25.00, "PAYMENT", null);
		transactionList.add(transaction);
		
		TransactionResult result = TransactionService.calculateRelativeBalance("ACC334455", transactionList );
		assertEquals(-25.00, result.getRelativeBalance(), 0.0);
		
	}
}
