# User/Bank Account Management Application

This is a simple user/bank account management application built using Spring Boot and Spring Data JPA. It allows users to create accounts, manage their bank accounts, and perform basic operations such as depositing and withdrawing funds.

## Features

- **User Management**: Allows users to create accounts with a unique username and password.
- **Bank Account Management**: Allows users to manage their bank accounts, including creating new accounts and performing transactions.
- **Basic Transactions**: Supports basic banking transactions such as depositing and withdrawing funds from bank accounts.
- **Data Persistence**: Utilizes Spring Data JPA for database interaction, ensuring data persistence and retrieval.

## Technologies Used

- **Spring Boot**: For building and managing the application.
- **Spring Data JPA**: For database interaction and data access.
- **Java Persistence API (JPA)**: For object-relational mapping.
- **Jakarta EE**: For enterprise-level Java development.
- **Git**: For version control and collaboration.
- **GitHub**: Hosting the repository and managing project development.

## Getting Started

To run this application locally, follow these steps:

1. Clone the repository to your local machine:
git clone <https://github.com/Brianlxm/PairedProgrammingProject.git>

2. Navigate to the project directory:
cd user-bank-account-management

3. Build and run the application using Maven:
mvn spring-boot:run

4. Access the application in your web browser at `http://localhost:8080`.

# Documentation

## UserController Class
### getAllUsers()
- **Description:** Retrieves all users from the database.
- **HTTP Method:** GET
- **Endpoint:** `/users`
- **Response:** Returns a list of `User` objects.
- **Example:**
    ```http
    GET /users
    ```
  **Response:**
    ```json
    [
        {
            "userId": 1,
            "username": "example1",
            ...
        },
        {
            "userId": 2,
            "username": "example2",
            ...
        },
        ...
    ]
    ```

### getUserById(int userId)
- **Description:** Retrieves a user by their ID from the database.
- **HTTP Method:** GET
- **Endpoint:** `/users/{userId}`
- **Path Variable:** `userId` - The ID of the user to retrieve.
- **Response:** Returns the user object with the specified ID or an error message if the user is not found.
- **Example:**
    ```http
    GET /users/123
    ```
  **Response:**
    ```json
    {
        "userId": 123,
        "username": "example",
        ...
    }
    ```

### addNewUser(User newUser)
- **Description:** Adds a new user to the database.
- **HTTP Method:** POST
- **Endpoint:** `/users`
- **Request Body:** JSON object representing the new user.
- **Response:** Returns a success message or an error message if the user addition fails.
- **Example:**
    ```http
    POST /users
    Content-Type: application/json

    {
        "username": "newuser",
        "password": "password",
        "name" : "Lebron James"
    }
    ```
  **Response:**
    ```json
    "User added successfully"
    ```

### updateUser(int userId, User updatedUser)
- **Description:** Updates an existing user in the database.
- **HTTP Method:** PUT
- **Endpoint:** `/users/{userId}`
- **Path Variable:** `userId` - The ID of the user to update.
- **Request Body:** JSON object representing the updated user.
- **Response:** Returns a success message or an error message if the user update fails.
- **Example:**
    ```http
    PUT /users/123
    Content-Type: application/json

    {
        "username": "updateduser",
        "password": "newpassword",
        "name" : "new name"
    }
    ```
  **Response:**
    ```json
    "User updated successfully"
    ```

### getAccountsByUserId(int userId)
- **Description:** Retrieves all bank accounts associated with a user from the database.
- **HTTP Method:** GET
- **Endpoint:** `/users/accounts/{userId}`
- **Path Variable:** `userId` - The ID of the user whose accounts to retrieve.
- **Response:** Returns a list of `BankAccount` objects belonging to the specified user.
- **Example:**
    ```http
    GET /users/accounts/123
    ```
  **Response:**
    ```json
    [
        {
            "accountId": 1,
            "account_balance": "123.45",
            ...
        },
        {
            "accountId": 2,
            "account_balance": "123.45",
            ...
        },
        ...
    ]
    ```

## BankAccountController Class

### getAllBankAccounts()
- **Description:** Retrieves all bank accounts from the database.
- **HTTP Method:** GET
- **Endpoint:** `/bankaccounts`
- **Response:** Returns a list of `BankAccount` objects.
- **Example:**
    ```http
    GET /bankaccounts
    ```
  **Response:**
    ```json
    [
        {
            "accountId": 1,
            "account_balance": "123.45",
            ...
        },
        {
            "accountId": 2,
            "account_balance": "123.45",
            ...
        },
        ...
    ]
    ```

### getAccountById(int accountId)
- **Description:** Retrieves a bank account by its ID from the database.
- **HTTP Method:** GET
- **Endpoint:** `/bankaccounts/{accountId}`
- **Path Variable:** `accountId` - The ID of the bank account to retrieve.
- **Response:** Returns the bank account object with the specified ID or an error message if the account is not found.
- **Example:**
    ```http
    GET /bankaccounts/123
    ```
  **Response:**
    ```json
    {
        "accountId": 123,
        "account_balance": "123.45",
        ...
    }
    ```

### addBankAccount(BankAccount bankAccount, int userId)
- **Description:** Inserts a new bank account into the database for a specific user.
- **HTTP Method:** POST
- **Endpoint:** `/bankaccounts/{userId}`
- **Path Variable:** `userId` - The ID of the user for whom the bank account is being added.
- **Request Body:** JSON object representing the new bank account.
- **Response:** Returns a success message or an error message if the bank account addition fails.
- **Example:**
    ```http
    POST /bankaccounts/123
    Content-Type: application/json

    {
        "account_balance": "123.45",
        ...
    }
    ```
  **Response:**
    ```json
    "Bank account added successfully"
    ```

### deleteBankAccount(int accountId)
- **Description:** Deletes a bank account from the database.
- **HTTP Method:** DELETE
- **Endpoint:** `/bankaccounts/{accountId}`
- **Path Variable:** `accountId` - The ID of the bank account to delete.
- **Response:** Returns a success message or an error message if the bank account deletion fails.
- **Example:**
    ```http
    DELETE /bankaccounts/123
    ```
  **Response:**
    ```json
    "Bank account deleted successfully"
    ```


## Contributing

Contributions to this project are welcome! If you have any suggestions, bug fixes, or feature requests, please feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
