package com.michaelmuther;

import java.time.LocalDate;
import java.util.ArrayList;

public class SourceTrialBalance extends AbstractTrialBalance {

    public SourceTrialBalance(String companyName, LocalDate date, ArrayList<GLAccount> accounts) {
        super(companyName, date, accounts);
    }
}
