# transaction
Transaction web project to calculate relative account balance for the given account number and timeframe

- This is a servlet based web project. It uses MySQL database to store the transactions.

- Use the following query to create TRANSACTION table in MySQL:

CREATE TABLE TRANSACTION (
TransactionId varchar(255) PRIMARY KEY,
FromAccountId varchar(100) NOT NULL,
ToAccountId varchar(100) NOT NULL,
CreateAt datetime NOT NULL,
Amount double NOT NULL,
TransactionType varchar(20) NOT NULL,
relatedTransaction varchar(255)
);

- Modify the Database configurations such as JDBC Url, Database name, Username and Password in PersistenceHelper.java

- Configure the WAR in Web Server and start the server.

- While starting up, the application imports the transactions data from transactions.csv into MySQL TRANSACTION table.

- After server started, the user can visit the web page by accessing \<WebServerURL\>/transaction.

- In the home page, user can input Account number and time frame. When user submits, the system retrieves the relavant transactions, calculate the relative account balance and displays the result to the user.

- If there are no transactions found for the given account number and timeframe, then "No Transactions found" message displayed.
