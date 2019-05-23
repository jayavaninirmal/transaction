package com.transaction.util;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVReader;
import com.transaction.Transaction;
import com.transaction.TransactionResult;

/**
 * @author Jayavani Swaminathan
 */
public class TransactionService {

	public static void importTransactions() throws IOException, SQLException {
		
		Connection conn = PersistenceHelper.getConnection();
		
		// Read data from transactions.csv
		InputStream is = TransactionService.class.getClassLoader().getResourceAsStream("transactions.csv");

		try (CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {

            	String transactionId = nextLine[0].trim();
     	        String fromAccountId = nextLine[1].trim();
     	        String toAccountId = nextLine[2].trim();
     	        String createAtString = nextLine[3].trim();
     	        String amountString = nextLine[4].trim();
     	        String transactionType = nextLine[5].trim();
     	        String relatedTransaction;
     	        if("REVERSAL".equals(transactionType.trim())) {
     	        	
     	        	relatedTransaction = nextLine[6].trim();
     	        } else {
     	        	relatedTransaction = null;
     	        }
     	        
     	        
     	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
     	        LocalDateTime createAt = LocalDateTime.parse(createAtString, formatter);
     	        
     	        double amount = Double.parseDouble(amountString);
     	        
     	        Transaction t = new Transaction(transactionId, fromAccountId, toAccountId, createAt, amount, transactionType, relatedTransaction );
     	        
     	        //Insert transaction to database
     	        insertTransaction(conn, t);
            }
        }
	    if(conn!=null) {
	    	conn.close();
	    }
	           
	}
	
	public static void insertTransaction(Connection conn, Transaction t) {
		PreparedStatement preparedStmt = null;
	    try { 

	      String query = "insert into TRANSACTION (TransactionId, FromAccountId, ToAccountId,"
	      		+ "CreateAt, Amount, TransactionType, RelatedTransaction) "
	      		+ "values (?,?,?,?,?,?,?)";
	      
	      preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, t.getTransactionId());
	      preparedStmt.setString (2, t.getFromAccountId());
	      preparedStmt.setString (3, t.getToAccountId());
	      
	      preparedStmt.setObject (4, Timestamp.valueOf(t.getCreateAt()));
	      preparedStmt.setDouble (5, t.getAmount());
	      preparedStmt.setString (6, t.getTransactionType());
	      preparedStmt.setString (7, t.getRelatedTransaction());
	      
	      preparedStmt.execute();
	      System.out.println("Transaction " + t.getTransactionId() + " inserted successfully");
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }
	    finally{
	      try{
	         if(preparedStmt!=null)
	        	 preparedStmt.close();
	      }catch(SQLException se2){
	    	  se2.printStackTrace();
	      }
	   }
	   
	}
	
	public static List<Transaction> retrieveTransaction(String accountId, String fromDateString, String toDateString) {
		List<Transaction> transactionList = new ArrayList<Transaction>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH); 
	    LocalDateTime fromDate = LocalDateTime.parse(fromDateString, formatter);
	    LocalDateTime toDate = LocalDateTime.parse(toDateString, formatter);
		
		Connection conn = PersistenceHelper.getConnection();
		
		PreparedStatement preparedStmt = null;
	    try { 

	      String query = "select * from TRANSACTION where (FromAccountId=? or ToAccountId=?)"
	    			+ " and CreateAt between ? and ?";
	      preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, accountId);
	      preparedStmt.setString (2, accountId);
	      preparedStmt.setObject (3, Timestamp.valueOf(fromDate));
	      preparedStmt.setObject (4, Timestamp.valueOf(toDate));
	      
	      
	      ResultSet rs = preparedStmt.executeQuery();
	      while(rs.next()) {
	    	String transactionId = rs.getString(1);
   	        String fromAccountId = rs.getString(2);
   	        String toAccountId = rs.getString(3);
   	        String createAtString = rs.getString(4);
   	        Double amount = rs.getDouble(5);
   	        String transactionType = rs.getString(6);
   	        String relatedTransaction = rs.getString(7);
   	        
   	       System.out.println(createAtString);
   	       
   	    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"); 
	        LocalDateTime createAt = LocalDateTime.parse(createAtString, formatter1);
	        
	        Transaction t = new Transaction(transactionId, fromAccountId, toAccountId, createAt, amount, transactionType, relatedTransaction );
	        transactionList.add(t);
	      }
	      
	    } catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    finally{
		      try{
		         if(preparedStmt!=null)
		        	 preparedStmt.close();
		      }catch(SQLException se2){
		    	  se2.printStackTrace();
		      }
		   }
		
		return transactionList;
	}
	
	
public static TransactionResult calculateRelativeBalance(String accountId, List<Transaction> transactionList) {
		
		double relativeBalance = 0.0;
		int transactionCount = 0;
		TransactionResult result = null;
		
		for(Transaction t : transactionList) {
			result = new TransactionResult();
			if("PAYMENT".equalsIgnoreCase(t.getTransactionType())) {
				if(accountId.equals(t.getFromAccountId())) {
					relativeBalance = relativeBalance - t.getAmount();
					transactionCount++;
				} else if(accountId.equals(t.getToAccountId())) {
					relativeBalance = relativeBalance + t.getAmount();
					transactionCount++;
				}
			} else if ("REVERSAL".equalsIgnoreCase(t.getTransactionType())) {
				if(accountId.equals(t.getFromAccountId())) {
					relativeBalance = relativeBalance + t.getAmount();
					transactionCount--;
				} else if(accountId.equals(t.getToAccountId())) {
					relativeBalance = relativeBalance - t.getAmount();
					transactionCount--;
				}
			}
			
		}
		if (result != null ) {
			result.setRelativeBalance(relativeBalance);
			result.setTransactionCount(transactionCount);
		}
		
		return result;
		
	}
}