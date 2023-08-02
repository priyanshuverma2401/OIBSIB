import java.util.*;

class User {
    private String userId;
    private String userPin;
    private double balance;

    public User(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(User recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            return true;
        }
        return false;
    }

    public void showTransactionHistory() {
        // Implement transaction history logic here
        System.out.println("Transaction History:");
        // Display transaction history
    }
}

class ATM {
    private User user;
    private Scanner scanner;

    public ATM(User user) {
        this.user = user;
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n==== ATM Menu ====");
        System.out.println("1. Transaction History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
    }

    public void performTransaction() {
        boolean quit = false;

        while (!quit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    user.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter the amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    boolean isWithdrawn = user.withdraw(withdrawAmount);
                    if (isWithdrawn) {
                        System.out.println("Withdraw successful.");
                    } else {
                        System.out.println("Insufficient balance. Withdraw failed.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    user.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 4:
                    System.out.print("Enter recipient's user id: ");
                    String recipientId = scanner.nextLine();
                    User recipient = findUserById(recipientId);
                    if (recipient == null) {
                        System.out.println("Recipient not found.");
                        break;
                    }
                    System.out.print("Enter the amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    boolean isTransferred = user.transfer(recipient, transferAmount);
                    if (isTransferred) {
                        System.out.println("Transfer successful.");
                    } else {
                        System.out.println("Insufficient balance. Transfer failed.");
                    }
                    break;
                case 5:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        System.out.println("Thank you for using the ATM.");
    }

    private User findUserById(String userId) {
        // Implement logic to find user by userId (e.g., search in a list of users)
        // If found, return the User object; otherwise, return null
        // Example:
        // for (User user : userList) {
        //     if (user.getUserId().equals(userId)) {
        //         return user;
        //     }
        // }
        // return null;

        // For the simplicity of this example, let's assume there's only one user
        // and their userId is "user123".
        if (userId.equals("user123")) {
            return new User("user123", "1234", 1000.0);
        }
        return null;
    }
}

public class ATMApp {
    public static void main(String[] args) {
        // For the simplicity of this example, let's assume there's only one user
        // and their userId is "user123" and pin is "1234".
        User user = new User("user123", "1234", 1000.0);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (userId.equals(user.getUserId()) && pin.equals(user.getUserPin())) {
            System.out.println("Login successful! Welcome, " + user.getUserId() + ".");
            ATM atm = new ATM(user);
            atm.performTransaction();
        } else {
            System.out.println("Invalid User ID or PIN. Login failed.");
        }

        scanner.close();
    }
}


