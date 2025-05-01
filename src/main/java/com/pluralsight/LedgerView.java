package com.pluralsight;

import java.util.List;

// handles displaying the ledger view to user
public class LedgerView {
    public static void showLedger(List<Transaction> transactions) {

        //sorts the transactions by descending date
        transactions.sort((a, b) -> b.getDate().compareTo(a.getDate()));
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}