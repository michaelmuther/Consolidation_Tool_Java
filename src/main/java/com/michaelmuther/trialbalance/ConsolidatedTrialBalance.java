package com.michaelmuther.trialbalance;

import java.time.LocalDate;
import java.util.HashMap;

public class ConsolidatedTrialBalance extends AbstractTrialBalance {

    public ConsolidatedTrialBalance(LocalDate date, HashMap<Integer, GLAccount> accounts) {
        super("Group Consolidation", date, accounts);
    }
}
