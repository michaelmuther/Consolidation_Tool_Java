package com.michaelmuther;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.TreeMap;

abstract class AbstractTrialBalance {

    private final String companyName;
    private final LocalDate date;
    private final HashMap<Integer, GLAccount> accounts;
    private final boolean isBalanced;

    public AbstractTrialBalance(String companyName, LocalDate date, HashMap<Integer, GLAccount> accounts) {
        this.companyName = companyName;
        this.date = date;
        this.accounts = accounts;
        this.isBalanced = isTrialBalanceBalanced();
    }

//    public String getCompanyName() {
//        return companyName;
//    }

    public LocalDate getDate() {
        return date;
    }

    public HashMap<Integer, GLAccount> getAccounts() {
        return accounts;
    }

    public boolean isBalanced() {
        return isBalanced;
    }

    // this is not working, it needs to be tested
    private boolean isTrialBalanceBalanced() {
//        return true;
//        accounts.values().forEach(i-> System.out.println("Account: " + i.getNumber() + " Balance: " + i.getBalance()));

        boolean isBalanced = accounts.values()
            .stream()
            .map(GLAccount::getBalance)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .equals(BigDecimal.ZERO);
        System.out.println(companyName + " is balanced: " + isBalanced);
//        return isBalanced;
        return true;
    }

    public void printTrialBalance() {
        System.out.println("Company Name: " + companyName + " Date: " + date);
        TreeMap<Integer, GLAccount> sortedAccounts = new TreeMap<>(accounts);
        sortedAccounts.values().forEach(i -> System.out.println(i.getNumber() + " " + i.getBalance()));
    }
}
