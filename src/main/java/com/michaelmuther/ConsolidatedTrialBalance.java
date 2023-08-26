package com.michaelmuther;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConsolidatedTrialBalance extends AbstractTrialBalance {

    public ConsolidatedTrialBalance(LocalDate date, ArrayList<GLAccount> accounts) {
        super("Group Consolidation", date, accounts);
    }
}
