import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 5;
        int totalRounds = 3;
        int score = 0;

        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("You have " + maxAttempts + " attempts to guess the number.");
        System.out.println("You will play " + totalRounds + " rounds. Let's get started!");

        for (int round = 1; round <= totalRounds; round++) {
            System.out.println("\nRound " + round);
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessed = false;

            while (attempts < maxAttempts) {
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess between " + minRange + " and " + maxRange + ": ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number " + targetNumber + " correctly in " + attempts + " attempts.");
                    score += maxAttempts - attempts + 1;
                    guessed = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }
            }

            if (!guessed) {
                System.out.println("Sorry! The correct number was " + targetNumber + ". Moving on to the next round.");
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Your total score: " + score);
        scanner.close();
    }
}

