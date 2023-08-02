import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class TrainReservationSystem {
    static HashMap<String, String> users = new HashMap<>();
    static List<Reservation> reservations = new ArrayList<>();

    static class Reservation {
        String username;
        int trainNumber;
        String trainName;
        String classType;
        String dateOfJourney;
        String source;
        String destination;

        Reservation(String username, int trainNumber, String classType, String dateOfJourney, String source, String destination) {
            this.username = username;
            this.trainNumber = trainNumber;
            this.trainName = "Train Name"; // You can fetch the actual train name from a database based on trainNumber.
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.source = source;
            this.destination = destination;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Sample data for testing purposes.
        users.put("priyanshu@2522", "ilovejava");
        

        while (!exit) {
            System.out.println("\n1. Login");
            System.out.println("2. Reservation System");
            System.out.println("3. Cancellation Form");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character.

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    if (isLoggedIn(scanner)) {
                        reservationSystem(scanner);
                    }
                    break;
                case 3:
                    if (isLoggedIn(scanner)) {
                        cancellationForm(scanner);
                    }
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    static boolean isLoggedIn(Scanner scanner) {
        if (users.isEmpty()) {
            System.out.println("No users registered yet. Please sign up first.");
            return false;
        }

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            return true;
        } else {
            System.out.println("Invalid username or password. Please try again.");
            return false;
        }
    }

    static void reservationSystem(Scanner scanner) {
        System.out.println("\n--- Reservation System ---");
        System.out.print("Enter Train Number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character.
        System.out.print("Enter Class Type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();

        String username = "user1"; // Assuming the user is already logged in.
        reservations.add(new Reservation(username, trainNumber, classType, dateOfJourney, source, destination));
        System.out.println("Reservation Successful!");
    }

    static void cancellationForm(Scanner scanner) {
        System.out.println("\n--- Cancellation Form ---");
        System.out.print("Enter PNR Number: ");
        int pnrNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character.

        boolean found = false;
        for (Reservation reservation : reservations) {
            if (reservation.trainNumber == pnrNumber) {
                found = true;
                System.out.println("Reservation Details:");
                System.out.println("Train Number: " + reservation.trainNumber);
                System.out.println("Train Name: " + reservation.trainName);
                System.out.println("Class Type: " + reservation.classType);
                System.out.println("Date of Journey: " + reservation.dateOfJourney);
                System.out.println("Source: " + reservation.source);
                System.out.println("Destination: " + reservation.destination);
                System.out.print("Press 'ok' to confirm cancellation: ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (confirm.equals("ok")) {
                    reservations.remove(reservation);
                    System.out.println("Reservation Canceled Successfully!");
                } else {
                    System.out.println("Cancellation Aborted.");
                }
                break;
            }
        }

        if (!found) {
            System.out.println("PNR Number not found. Please check the PNR and try again.");
        }
    }
}
