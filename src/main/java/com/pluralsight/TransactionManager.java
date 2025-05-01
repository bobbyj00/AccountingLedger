package com.pluralsight;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

//handles loading and saving transactions to and from the file
public class TransactionManager {
    private static final String FILE_NAME = "transactions.csv";

    //loads all transactions from file
    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String desc = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date, time, desc, vendor, amount));
            }
        } catch (IOException e) {
            System.out.println("No previous transactions found. Starting fresh.");
        }
        return transactions;
    }

    //saves new transactions to file
    public void saveTransaction(Transaction t) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(t.toString());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction.");
        }
    }
}