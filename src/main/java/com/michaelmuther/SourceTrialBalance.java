package com.michaelmuther;

import java.time.LocalDate;
import java.util.HashMap;

public class SourceTrialBalance extends AbstractTrialBalance {

    public SourceTrialBalance(String companyName, LocalDate date, HashMap<Integer,GLAccount> accounts) {
        super(companyName, date, accounts);
    }
}
