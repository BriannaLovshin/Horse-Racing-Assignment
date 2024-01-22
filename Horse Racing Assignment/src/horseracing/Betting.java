package horseracing;

import java.util.Scanner;

public class Betting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean betSuccessful = false;

        while (!betSuccessful) {
            // find out how much is in the user's wallet
            System.out.print("Enter the amount in your wallet: $");
            double walletAmount = scanner.nextDouble();

            // finds out how much they want to be on win
            System.out.print("Enter the amount to bet on Win: $");
            double winBetAmount = scanner.nextDouble();
                if(winBetAmount>0){
                    System.out.print("which horse do you want to be on for Win:(number) ");
                    double winHorseNumber = scanner.nextDouble();
                }

            // finds out the amount they want to be on Place
            System.out.print("Enter the amount to bet on Place: $");
            double placeBetAmount = scanner.nextDouble();
            if(winBetAmount>0){
                System.out.print("which horse do you want to be on for Win:(number) ");
                double placeHorseNumber = scanner.nextDouble();
            }

            // finds out the amount they want to bet on Show
            System.out.print("Enter the amount to bet on Show: $");
            double showBetAmount = scanner.nextDouble();
            if(winBetAmount>0){
                System.out.print("which horse do you want to be on for Win:(number) ");
                double showHorseNumber = scanner.nextDouble();
            }

            double totalBetAmount = winBetAmount + placeBetAmount + showBetAmount;

            // check if the total betting amount is lower or equal to the wallet's amount
            if (totalBetAmount > walletAmount) {
                System.out.println("There are Insufficient amound of funds. We cannot place those bets. Please try again.");
            } else {
                System.out.println("Bets placed successfully!");
                System.out.println("Total amount bet: $" + totalBetAmount);
                betSuccessful = true;
            }
        }
        scanner.close();
    }

    // odds
    // how much we have to give back to the people
}
