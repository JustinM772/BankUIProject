import java.util.Scanner;
public class ATM {
    Scanner scan = new Scanner(System.in);
    private String name;
    private int PIN;
    private Customer c;
    private Account savings;
    private Account checking;
    private int guess;
    private int userChoice;
    private int accountTransactionNumber;
    private int securityTransactionNumber;
    private int transactionNumber;
    private TransactionHistory transactionHistory;
    private String doAnythingElse = "yes";


    public ATM() {
    }
    public void start() {
        welcomeUser();
        userActions();
        endProgram();
    }
    public void welcomeUser() {
        System.out.println(ConsoleUtility.YELLOW + "Welcome to the ATM!");
        System.out.print("Enter your name: ");
        name = scan.nextLine();
        System.out.print("Choose a PIN: " + ConsoleUtility.RESET);
        PIN = scan.nextInt();
        c = new Customer(name, PIN);
        savings = new Account(name, 0);
        checking = new Account(name, 0);
        transactionHistory = new TransactionHistory();
    }


    public void userActions() {
        while (userChoice != 7 && doAnythingElse.equals("yes")) {
            System.out.print(ConsoleUtility.BLUE + "Enter your PIN: " + ConsoleUtility.RESET);
            guess = scan.nextInt();
            while (guess != c.getPIN()) {
                System.out.print(ConsoleUtility.RED + "The pin entered is not correct. Enter again: " + ConsoleUtility.RESET);
                guess = scan.nextInt();
            }
            System.out.print(ConsoleUtility.PURPLE + "1. Withdraw money\n2. Deposit money\n3. Transfer money between accounts\n4. Get account balances\n5. Get transaction history\n6. Change PIN\n7. Exit\n" + ConsoleUtility.RESET);
            userChoice = scan.nextInt();
            scan.nextLine();
            if (userChoice == 1) {
                System.out.print(ConsoleUtility.CYAN + "Choose an account(s/c): ");
                String accountChoice = scan.nextLine();
                if (accountChoice.equals("s")) {
                    System.out.print("Enter an amount to withdraw(can only give out $5 and $20 bills): " + ConsoleUtility.RESET);
                    int amountToWithdraw = scan.nextInt();
                    scan.nextLine();
                    if (amountToWithdraw > savings.getBalance()) {
                        String action = "Insufficient funds";
                        System.out.println(ConsoleUtility.RED + action + ConsoleUtility.RESET);
                        int numZeroes;
                        if (accountTransactionNumber > 999) {
                            numZeroes = 0;
                        } else if (accountTransactionNumber > 99) {
                            numZeroes = 1;
                        } else if (accountTransactionNumber > 9) {
                            numZeroes = 2;
                        } else {
                            numZeroes = 3;
                        }
                        String IDInfo = "Transaction ID: A";
                        String ID = "A";
                        for (int i = 0; i < numZeroes; i++) {
                            IDInfo += "0";
                            ID += "0";
                        }
                        IDInfo += accountTransactionNumber;
                        ID += accountTransactionNumber;
                        accountTransactionNumber++;
                        System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                        Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), false);
                        transactionHistory.addTransaction(t); // every transaction is recorded, whether it is successful or unsuccessful
                        System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                        doAnythingElse = scan.nextLine();
                    } else if (amountToWithdraw % 5 != 0) {
                        String action = "Invalid amount";
                        System.out.println(ConsoleUtility.RED + action + ConsoleUtility.RESET);
                        int numZeroes;
                        if (accountTransactionNumber > 999) {
                            numZeroes = 0;
                        } else if (accountTransactionNumber > 99) {
                            numZeroes = 1;
                        } else if (accountTransactionNumber > 9) {
                            numZeroes = 2;
                        } else {
                            numZeroes = 3;
                        }
                        String IDInfo = "Transaction ID: A";
                        String ID = "A";
                        for (int i = 0; i < numZeroes; i++) {
                            IDInfo += "0";
                            ID += "0";
                        }
                        IDInfo += accountTransactionNumber;
                        ID += accountTransactionNumber;
                        accountTransactionNumber++;
                        System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                        Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), false);
                        transactionHistory.addTransaction(t);
                        System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                        doAnythingElse = scan.nextLine();
                    } else {
                        System.out.print(ConsoleUtility.CYAN + "How many $5 bills to withdraw? ");
                        int fiveDollarBills = scan.nextInt();
                        System.out.print("How many $20 bills to withdraw? " + ConsoleUtility.RESET);
                        int twentyDollarBills = scan.nextInt();
                        scan.nextLine();
                        if (fiveDollarBills * 5 + twentyDollarBills * 20 != amountToWithdraw) { // checks to see if choice of bills adds up to withdrawal amount; transaction fails if not
                            String action = "Invalid choice of bills";
                            System.out.println(ConsoleUtility.RED + action + ConsoleUtility.RESET);
                            int numZeroes;
                            if (accountTransactionNumber > 999) {
                                numZeroes = 0;
                            } else if (accountTransactionNumber > 99) {
                                numZeroes = 1;
                            } else if (accountTransactionNumber > 9) {
                                numZeroes = 2;
                            } else {
                                numZeroes = 3;
                            }
                            String IDInfo = "Transaction ID: A";
                            String ID = "A";
                            for (int i = 0; i < numZeroes; i++) {
                                IDInfo += "0";
                                ID += "0";
                            }
                            IDInfo += accountTransactionNumber;
                            ID += accountTransactionNumber;
                            accountTransactionNumber++;
                            System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                            Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), false);
                            transactionHistory.addTransaction(t);
                            System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET); // this affects whether the program continues or not, question is asked after every transaction
                            doAnythingElse = scan.nextLine();
                        } else {
                            savings.updateBalance(savings.getBalance() - amountToWithdraw);
                            String action = "Successfully withdrew $" + amountToWithdraw + " from Savings";
                            System.out.println(ConsoleUtility.GREEN + action + ConsoleUtility.RESET);
                            int numZeroes;
                            if (accountTransactionNumber > 999) {
                                numZeroes = 0;
                            } else if (accountTransactionNumber > 99) {
                                numZeroes = 1;
                            } else if (accountTransactionNumber > 9) {
                                numZeroes = 2;
                            } else {
                                numZeroes = 3;
                            }
                            String IDInfo = "Transaction ID: A";
                            String ID = "A";
                            for (int i = 0; i < numZeroes; i++) {
                                IDInfo += "0";
                                ID += "0";
                            }
                            IDInfo += accountTransactionNumber;
                            ID += accountTransactionNumber;
                            accountTransactionNumber++;
                            System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                            Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), true);
                            transactionHistory.addTransaction(t);
                            System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                            doAnythingElse = scan.nextLine();
                        }
                    }
                } else if (accountChoice.equals("c")) { // processes above are repeated for checking account, just modified to change checking balance instead
                    System.out.print("Enter an amount to withdraw(can only give out $5 and $20 bills): " + ConsoleUtility.RESET);
                    int amountToWithdraw = scan.nextInt();
                    scan.nextLine();
                    if (amountToWithdraw > checking.getBalance()) {
                        String action = "Insufficient funds";
                        System.out.println(ConsoleUtility.RED + action + ConsoleUtility.RESET);
                        int numZeroes;
                        if (accountTransactionNumber > 999) {
                            numZeroes = 0;
                        } else if (accountTransactionNumber > 99) {
                            numZeroes = 1;
                        } else if (accountTransactionNumber > 9) {
                            numZeroes = 2;
                        } else {
                            numZeroes = 3;
                        }
                        String IDInfo = "Transaction ID: A";
                        String ID = "A";
                        for (int i = 0; i < numZeroes; i++) {
                            IDInfo += "0";
                            ID += "0";
                        }
                        IDInfo += accountTransactionNumber;
                        ID += accountTransactionNumber;
                        accountTransactionNumber++;
                        System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                        Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), false);
                        transactionHistory.addTransaction(t);
                        System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                        doAnythingElse = scan.nextLine();
                    } else if (amountToWithdraw % 5 != 0) {
                        String action = "Invalid amount";
                        System.out.println(ConsoleUtility.RED + action + ConsoleUtility.RESET);
                        int numZeroes;
                        if (accountTransactionNumber > 999) {
                            numZeroes = 0;
                        } else if (accountTransactionNumber > 99) {
                            numZeroes = 1;
                        } else if (accountTransactionNumber > 9) {
                            numZeroes = 2;
                        } else {
                            numZeroes = 3;
                        }
                        String IDInfo = "Transaction ID: A";
                        String ID = "A";
                        for (int i = 0; i < numZeroes; i++) {
                            IDInfo += "0";
                            ID += "0";
                        }
                        IDInfo += accountTransactionNumber;
                        ID += accountTransactionNumber;
                        accountTransactionNumber++;
                        System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                        Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), false);
                        transactionHistory.addTransaction(t);
                        System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                        doAnythingElse = scan.nextLine();
                    } else {
                        System.out.print(ConsoleUtility.CYAN + "How many $5 bills to withdraw? ");
                        int fiveDollarBills = scan.nextInt();
                        System.out.print("How many $20 bills to withdraw? " + ConsoleUtility.RESET);
                        int twentyDollarBills = scan.nextInt();
                        scan.nextLine();
                        if (fiveDollarBills * 5 + twentyDollarBills * 20 != amountToWithdraw) {
                            String action = "Invalid choice of bills";
                            System.out.println(ConsoleUtility.RED + action + ConsoleUtility.RESET);
                            int numZeroes;
                            if (accountTransactionNumber > 999) {
                                numZeroes = 0;
                            } else if (accountTransactionNumber > 99) {
                                numZeroes = 1;
                            } else if (accountTransactionNumber > 9) {
                                numZeroes = 2;
                            } else {
                                numZeroes = 3;
                            }
                            String IDInfo = "Transaction ID: A";
                            String ID = "A";
                            for (int i = 0; i < numZeroes; i++) {
                                IDInfo += "0";
                                ID += "0";
                            }
                            IDInfo += accountTransactionNumber;
                            ID += accountTransactionNumber;
                            accountTransactionNumber++;
                            System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                            Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), false);
                            transactionHistory.addTransaction(t);
                            System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                            doAnythingElse = scan.nextLine();
                        } else {
                            checking.updateBalance(checking.getBalance() - amountToWithdraw);
                            String action = "Successfully withdrew $" + amountToWithdraw + " from Checking";
                            System.out.println(ConsoleUtility.GREEN + action + ConsoleUtility.RESET);
                            int numZeroes;
                            if (accountTransactionNumber > 999) {
                                numZeroes = 0;
                            } else if (accountTransactionNumber > 99) {
                                numZeroes = 1;
                            } else if (accountTransactionNumber > 9) {
                                numZeroes = 2;
                            } else {
                                numZeroes = 3;
                            }
                            String IDInfo = "Transaction ID: A";
                            String ID = "A";
                            for (int i = 0; i < numZeroes; i++) {
                                IDInfo += "0";
                                ID += "0";
                            }
                            IDInfo += accountTransactionNumber;
                            ID += accountTransactionNumber;
                            accountTransactionNumber++;
                            System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                            Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), true);
                            transactionHistory.addTransaction(t);
                            System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                            doAnythingElse = scan.nextLine();
                        }
                    }
                }
            } else if (userChoice == 2) {
                System.out.print(ConsoleUtility.CYAN + "Choose an account(s/c): ");
                String accountChoice = scan.nextLine();
                if (accountChoice.equals("s")) {
                    System.out.print("Enter an amount: " + ConsoleUtility.RESET);
                    double amountToDeposit = scan.nextDouble();
                    scan.nextLine();
                    savings.updateBalance(savings.getBalance() + amountToDeposit);
                    String action = "Successfully deposited $" + amountToDeposit + " into Savings";
                    System.out.println(ConsoleUtility.GREEN + action + ConsoleUtility.RESET);
                    int numZeroes;
                    if (accountTransactionNumber > 999) {
                        numZeroes = 0;
                    } else if (accountTransactionNumber > 99) {
                        numZeroes = 1;
                    } else if (accountTransactionNumber > 9) {
                        numZeroes = 2;
                    } else {
                        numZeroes = 3;
                    }
                    String IDInfo = "Transaction ID: A";
                    String ID = "A";
                    for (int i = 0; i < numZeroes; i++) {
                        IDInfo += "0";
                        ID += "0";
                    }
                    IDInfo += accountTransactionNumber;
                    ID += accountTransactionNumber;
                    accountTransactionNumber++;
                    System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                    Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), true);
                    transactionHistory.addTransaction(t);
                    System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                    doAnythingElse = scan.nextLine();
                } else if (accountChoice.equals("c")) {
                    System.out.print("Enter an amount: " + ConsoleUtility.RESET);
                    double amountToDeposit = scan.nextDouble();
                    scan.nextLine();
                    checking.updateBalance(checking.getBalance() + amountToDeposit);
                    String action = "Successfully deposited $" + amountToDeposit + " into Checking";
                    System.out.println(ConsoleUtility.GREEN + action + ConsoleUtility.RESET);
                    int numZeroes;
                    if (accountTransactionNumber > 999) {
                        numZeroes = 0;
                    } else if (accountTransactionNumber > 99) {
                        numZeroes = 1;
                    } else if (accountTransactionNumber > 9) {
                        numZeroes = 2;
                    } else {
                        numZeroes = 3;
                    }
                    String IDInfo = "Transaction ID: A";
                    String ID = "A";
                    for (int i = 0; i < numZeroes; i++) {
                        IDInfo += "0";
                        ID += "0";
                    }
                    IDInfo += accountTransactionNumber;
                    ID += accountTransactionNumber;
                    accountTransactionNumber++;
                    System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                    Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), true);
                    transactionHistory.addTransaction(t);
                    System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                    doAnythingElse = scan.nextLine();
                }
            } else if (userChoice == 3) {
                System.out.print(ConsoleUtility.CYAN + "Choose an account to take money from(s/c): ");
                String accountChoice = scan.nextLine();
                if (accountChoice.equals("s")) {
                    System.out.print("Enter the amount: " + ConsoleUtility.RESET);
                    double amountToTransfer = scan.nextDouble();
                    scan.nextLine();
                    if (amountToTransfer > savings.getBalance()) {
                        String action = "Insufficient funds in this account";
                        System.out.println(ConsoleUtility.RED + action + ConsoleUtility.RESET);
                        int numZeroes;
                        if (accountTransactionNumber > 999) {
                            numZeroes = 0;
                        } else if (accountTransactionNumber > 99) {
                            numZeroes = 1;
                        } else if (accountTransactionNumber > 9) {
                            numZeroes = 2;
                        } else {
                            numZeroes = 3;
                        }
                        String IDInfo = "Transaction ID: A";
                        String ID = "A";
                        for (int i = 0; i < numZeroes; i++) {
                            IDInfo += "0";
                            ID += "0";
                        }
                        IDInfo += accountTransactionNumber;
                        ID += accountTransactionNumber;
                        accountTransactionNumber++;
                        System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                        Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), false);
                        transactionHistory.addTransaction(t);
                        System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                        doAnythingElse = scan.nextLine();
                    } else {
                        savings.updateBalance(savings.getBalance() - amountToTransfer);
                        checking.updateBalance(checking.getBalance() + amountToTransfer); // both balances are changed as needed
                        String action = "Successfully transferred $" + amountToTransfer + " from Savings to Checking";
                        System.out.println(ConsoleUtility.GREEN + action + ConsoleUtility.RESET);
                        int numZeroes;
                        if (accountTransactionNumber > 999) {
                            numZeroes = 0;
                        } else if (accountTransactionNumber > 99) {
                            numZeroes = 1;
                        } else if (accountTransactionNumber > 9) {
                            numZeroes = 2;
                        } else {
                            numZeroes = 3;
                        }
                        String IDInfo = "Transaction ID: A";
                        String ID = "A";
                        for (int i = 0; i < numZeroes; i++) {
                            IDInfo += "0";
                            ID += "0";
                        }
                        IDInfo += accountTransactionNumber;
                        ID += accountTransactionNumber;
                        accountTransactionNumber++;
                        System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                        Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), true);
                        transactionHistory.addTransaction(t);
                        System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                        doAnythingElse = scan.nextLine();
                    }
                } else if (accountChoice.equals("c")) {
                    System.out.print("Enter the amount: " + ConsoleUtility.RESET);
                    double amountToTransfer = scan.nextDouble();
                    scan.nextLine();
                    if (amountToTransfer > checking.getBalance()) {
                        String action = "Insufficient funds in this account";
                        System.out.println(ConsoleUtility.RED + action + ConsoleUtility.RESET);
                        int numZeroes;
                        if (accountTransactionNumber > 999) {
                            numZeroes = 0;
                        } else if (accountTransactionNumber > 99) {
                            numZeroes = 1;
                        } else if (accountTransactionNumber > 9) {
                            numZeroes = 2;
                        } else {
                            numZeroes = 3;
                        }
                        String IDInfo = "Transaction ID: A";
                        String ID = "A";
                        for (int i = 0; i < numZeroes; i++) {
                            IDInfo += "0";
                            ID += "0";
                        }
                        IDInfo += accountTransactionNumber;
                        ID += accountTransactionNumber;
                        accountTransactionNumber++;
                        System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                        Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), false);
                        transactionHistory.addTransaction(t);
                        System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                        doAnythingElse = scan.nextLine();
                    } else {
                        checking.updateBalance(checking.getBalance() - amountToTransfer);
                        savings.updateBalance(savings.getBalance() + amountToTransfer);
                        String action = "Successfully transferred $" + amountToTransfer + " from Checking to Savings";
                        System.out.println(ConsoleUtility.GREEN + action + ConsoleUtility.RESET);
                        int numZeroes;
                        if (accountTransactionNumber > 999) {
                            numZeroes = 0;
                        } else if (accountTransactionNumber > 99) {
                            numZeroes = 1;
                        } else if (accountTransactionNumber > 9) {
                            numZeroes = 2;
                        } else {
                            numZeroes = 3;
                        }
                        String IDInfo = "Transaction ID: A";
                        String ID = "A";
                        for (int i = 0; i < numZeroes; i++) {
                            IDInfo += "0";
                            ID += "0";
                        }
                        IDInfo += accountTransactionNumber;
                        ID += accountTransactionNumber;
                        accountTransactionNumber++;
                        System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                        Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), true);
                        transactionHistory.addTransaction(t);
                        System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                        doAnythingElse = scan.nextLine();
                    }
                }
            } else if (userChoice == 4) {
                System.out.println(ConsoleUtility.CYAN + "Savings Account: $" + savings.getBalance());
                System.out.println("Checking Account: $" + checking.getBalance() + ConsoleUtility.RESET);
                String action = "Successfully showed balances";
                System.out.println(ConsoleUtility.GREEN + action + ConsoleUtility.RESET);
                int numZeroes;
                if (securityTransactionNumber > 999) {
                    numZeroes = 0;
                } else if (securityTransactionNumber > 99) {
                    numZeroes = 1;
                } else if (securityTransactionNumber > 9) {
                    numZeroes = 2;
                } else {
                    numZeroes = 3;
                }
                String IDInfo = "Transaction ID: S"; // different type of transaction ID for different type of transaction
                String ID = "S";
                for (int i = 0; i < numZeroes; i++) {
                    IDInfo += "0";
                    ID += "0";
                }
                IDInfo += securityTransactionNumber;
                ID += securityTransactionNumber;
                securityTransactionNumber++;
                System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), true);
                transactionHistory.addTransaction(t);
                System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                doAnythingElse = scan.nextLine();
            } else if (userChoice == 5) {
                for (Transaction t : transactionHistory.getHistory()) {
                    System.out.println(t.getID());
                    System.out.println(t.getAction());
                    System.out.println(t.getSuccess());
                    System.out.println(ConsoleUtility.CYAN + "Savings Balance After Transaction: $" + t.getSavingsBalance());
                    System.out.println("Checking Balance After Transaction: $" + t.getCheckingBalance() + ConsoleUtility.CYAN);
                }
                String action = "Successfully showed transaction history";
                System.out.println(ConsoleUtility.GREEN + action + ConsoleUtility.RESET);
                int numZeroes;
                if (securityTransactionNumber > 999) {
                    numZeroes = 0;
                } else if (securityTransactionNumber > 99) {
                    numZeroes = 1;
                } else if (securityTransactionNumber > 9) {
                    numZeroes = 2;
                } else {
                    numZeroes = 3;
                }
                String IDInfo = "Transaction ID: S";
                String ID = "S";
                for (int i = 0; i < numZeroes; i++) {
                    IDInfo += "0";
                    ID += "0";
                }
                IDInfo += securityTransactionNumber;
                ID += securityTransactionNumber;
                securityTransactionNumber++;
                System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), true);
                transactionHistory.addTransaction(t);
                System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                doAnythingElse = scan.nextLine();
            } else if (userChoice == 6) {
                System.out.print(ConsoleUtility.CYAN + "Enter a new PIN: " + ConsoleUtility.RESET);
                int newPin = scan.nextInt();
                scan.nextLine();
                c.updatePIN(newPin);
                String action = "Successfully changed PIN"; // user now has to enter the new PIN and the old one(s) will fail
                System.out.println(ConsoleUtility.GREEN + action + ConsoleUtility.RESET);
                int numZeroes;
                if (securityTransactionNumber > 999) {
                    numZeroes = 0;
                } else if (securityTransactionNumber > 99) {
                    numZeroes = 1;
                } else if (securityTransactionNumber > 9) {
                    numZeroes = 2;
                } else {
                    numZeroes = 3;
                }
                String IDInfo = "Transaction ID: S";
                String ID = "S";
                for (int i = 0; i < numZeroes; i++) {
                    IDInfo += "0";
                    ID += "0";
                }
                IDInfo += securityTransactionNumber;
                ID += securityTransactionNumber;
                securityTransactionNumber++;
                System.out.println(IDInfo + "\nSavings Balance: $" + savings.getBalance() + "\nChecking Balance: $" + checking.getBalance());
                Transaction t = new Transaction(ID, action, savings.getBalance(), checking.getBalance(), true);
                transactionHistory.addTransaction(t);
                System.out.print(ConsoleUtility.YELLOW + "Do you want to do anything else?(yes/no) " + ConsoleUtility.RESET);
                doAnythingElse = scan.nextLine();
            } else if (userChoice == 7) {
                break;
            }
        }
    }

    public void endProgram() {
        System.out.println(ConsoleUtility.BLUE + "Thank you for choosing this ATM!" + ConsoleUtility.RESET);
    }
}
