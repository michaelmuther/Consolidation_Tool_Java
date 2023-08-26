package com.michaelmuther;

import java.time.LocalDate;
import java.util.List;

abstract class AbstractTrialBalance {

    String companyName;
    LocalDate date;
    List<GLAccount> accounts;

    public abstract void printTrialBalance();
}
