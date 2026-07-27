# Banking Transaction System

A Java-based banking transaction system implementing core banking operations such as account management, client registration, deposits, withdrawals, and transfers between accounts. Built as a console application with a modular architecture following object-oriented design principles.

## Features

- **Client Registration** — Register clients with CPF validation to prevent duplicates.
- **Account Types** — Support for both Checking Accounts (`CheckingAccount`) and Savings Accounts (`SavingsAccount`).
- **Transaction Management** — Record and track all banking transactions including deposits, withdrawals, transfers, and statement generation.
- **Balance Operations** — Deposit, withdraw, and transfer funds between accounts with validation.
- **Transaction History** — View complete transaction history per account with timestamps and status.
- **Custom Exceptions** — `BankException` for proper error handling throughout the system.

## Tech Stack

| Technology | Details |
|---|---|
| Language | Java |
| OOP Concepts | Inheritance, Polymorphism, Encapsulation, Enums |
| Data Structures | `ArrayList`, custom entity models |
| Build / IDE | Eclipse IDE |

## Project Structure

```
src/
├── app/
│   ├── Program.java          # Application entry point
│   ├── UI.java               # Console user interface
│   └── util/
│       └── InputReader.java  # Input handling utility
├── banking/
│   ├── BankService.java      # Core banking service layer
│   ├── BankException.java    # Custom exception handling
│   ├── Transaction.java      # Transaction entity
│   ├── entities/
│   │   ├── Account.java      # Base account class
│   │   ├── CheckingAccount.java
│   │   ├── SavingsAccount.java
│   │   └── Client.java
│   └── enums/
│       ├── AccountType.java
│       ├── ClientStatus.java
│       ├── TransactionStatus.java
│       └── TransactionType.java
└── module-info.java
```

## How to Run

1. Clone the repository.
2. Open the project in Eclipse IDE (Java 17+ recommended).
3. Run `Program.java` from the `app` package.

## License

MIT License — see the [LICENSE](LICENSE) file for details.
