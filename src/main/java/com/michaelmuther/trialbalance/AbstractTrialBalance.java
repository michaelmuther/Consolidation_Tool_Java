package com.michaelmuther.trialbalance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

public abstract class AbstractTrialBalance {

    private final String companyName;

    private final LocalDate date;

    private final HashMap<Integer, GLAccount> accounts;
    private final boolean isBalanced;
    public AbstractTrialBalance(String companyName, LocalDate date, HashMap<Integer, GLAccount> accounts) {
        this.companyName = companyName;
        this.date = date;
        this.accounts = accounts;
        this.isBalanced = isTrialBalanceBalanced();
//        System.out.println(companyName + " is balanced: " + isBalanced);
    }

    public LocalDate getDate() {
        return date;
    }

    public HashMap<Integer, GLAccount> getAccounts() {
        return accounts;
    }

    public String getCompanyName() {
        return companyName;
    }

    public boolean isBalanced() {
        return isBalanced;
    }

    private boolean isTrialBalanceBalanced() {

        BigDecimal trialBalanceTotal = accounts.values()
                .stream()
                .map(GLAccount::getBalance)
//                .peek(i -> System.out.println("gl account amount: " + i)) // for testing
                .reduce(BigDecimal.ZERO, BigDecimal::add);
//        System.out.println("trial balance int value:" + trialBalanceTotal.intValue()); // for testing
        boolean tbIsBalanced = trialBalanceTotal.intValue() == 0;
//        System.out.println(companyName + " is balanced: " + tbIsBalanced); // for testing
        return tbIsBalanced;
    }

    public void printTrialBalance() {
        System.out.println("Company Name: " + companyName + " Date: " + date);
        TreeMap<Integer, GLAccount> sortedAccounts = new TreeMap<>(accounts);
        sortedAccounts.values().forEach(i -> System.out.println(i.getNumber() + " " + i.getBalance()));
    }
}
