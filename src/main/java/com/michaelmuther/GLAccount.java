package com.michaelmuther;

import java.math.BigDecimal;

public class GLAccount {

//    public enum Type {ASSET, LIABILITY, EQUITY, REVENUE, EXPENSE, OTHER_EXPENSE}

    private final int number;
//    private final Type type;
    private final String name;
    private final BigDecimal balance;

    public GLAccount (int accountNumber, String accountName, BigDecimal balance) {
        this.number = accountNumber;
//        this.type = determineType(number);
        this.name = accountName;
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

//    public Type getType() {
//        return type;
//    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

//    private static Type determineType(int accountNumber) {
//        if (accountNumber < 200)
//            return GLAccount.Type.ASSET;
//        if (accountNumber < 300)
//            return Type.LIABILITY;
//        if (accountNumber < 400)
//            return GLAccount.Type.EQUITY;
//        if (accountNumber < 500)
//            return Type.REVENUE;
//        if (accountNumber < 600)
//            return Type.EXPENSE;
//        if (accountNumber < 700)
//            return Type.OTHER_EXPENSE;
//        return null;
//    }
}
