package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();

        while (true) {
            //home screen options
            System.out.println("\n--- Home ---");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            //users choices
            switch (choice) {
                case "D":
                    addTransaction(scanner, manager, true); // Deposit
                    break;
                case "P":
                    addTransaction(scanner, manager, false); // Payment
                    break;
                case "L":
                    List<Transaction> all = manager.loadTransactions();
                    LedgerView.showLedger(all); // Show ledger
                    break;
                case "X":
                    System.out.println("Exiting application.");
                    return; // Exit app
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    //helper method to display payments or deposits
    private static void addTransaction(Scanner scanner, TransactionManager manager, boolean isDeposit) {
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // make negative for payments
        if (!isDeposit) amount *= -1;

        //create and save transaction
        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), desc, vendor, amount);
        manager.saveTransaction(t);
        System.out.println("Transaction saved.");
    }
}
