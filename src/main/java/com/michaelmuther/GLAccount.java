package com.michaelmuther;

import java.math.BigDecimal;

public class GLAccount {

    public enum type {ASSET, LIABILITY, EQUITY, REVENUE, EXPENSE}

    BigDecimal balance = BigDecimal.ZERO;

    public GLAccount (BigDecimal balance) {
        this.balance = balance;
    }

}
