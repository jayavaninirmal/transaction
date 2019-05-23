# transaction
Transaction web project to calculate relative account balance for the given account number and timeframe

- This is a servlet based web project. It uses MySQL database to store the transactions.

- After configuring the WAR in Web Server, start the server.

- While starting up, the application imports the transactions data from transactions.csv into MySQL database.

- After server started, the user can visit the web page by accessing \<WebServerURL\>/transaction.

- In the home page, user can input Account number and time frame. When user submits, the system retrieves the relavant transactions, calculate the relative account balance and displays the result to the user.

- If there are no transactions found for the given account number and timeframe, then "No Transactions found" message displayed.
