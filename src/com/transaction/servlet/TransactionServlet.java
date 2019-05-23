package com.transaction.servlet;
 
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.transaction.Transaction;
import com.transaction.TransactionResult;
import com.transaction.util.TransactionService;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
 
/**
 * @author Jayavani Swaminathan
 */
@WebServlet(urlPatterns="/transaction") 
public class TransactionServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		// reading the user input
        String accountId = request.getParameter("accountId");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        
        //Retrieve transactions from DB for the given user input
        List<Transaction> transactionList = TransactionService.retrieveTransaction(accountId, fromDate, toDate);
        
        //Calculate relativeBalance for the selected transactions
        TransactionResult result = TransactionService.calculateRelativeBalance(accountId, transactionList);
        
		//Set the result in request object to display in browser
		request.setAttribute("TransactionResult", result);
		
		//Dispatching to transaction-result.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher(
		          "/WEB-INF/jsp/transaction-result.jsp");
		        dispatcher.forward(request, response);
    }
}