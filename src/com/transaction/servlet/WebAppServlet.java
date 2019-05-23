package com.transaction.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.transaction.util.TransactionService;

/**
 * @author Jayavani Swaminathan
 */
@WebServlet(urlPatterns="/home", loadOnStartup=1) 
public class WebAppServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException { 
		
		//During system start up, import the transactions.csv to database
		try {
			TransactionService.importTransactions();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
	
