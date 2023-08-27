package com.michaelmuther;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

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

    public String getCompanyName() {
        return companyName;
    }

    public LocalDate getDate() {
        return date;
    }

    public HashMap<Integer, GLAccount> getAccounts() {
        return accounts;
    }

    public boolean isBalanced() {
        return isBalanced;
    }

    private boolean isTrialBalanceBalanced() {
        return accounts.values()
            .stream()
            .map(GLAccount::getBalance)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .equals(BigDecimal.ZERO);
    }

    public void printTrialBalance() {
        System.out.println("Company Name: " + companyName + " Date: " + date);
        accounts.values().forEach(i -> System.out.println(i.getNumber() + " " + i.getBalance()));
    }
}
