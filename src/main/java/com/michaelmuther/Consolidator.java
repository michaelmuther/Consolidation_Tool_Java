package com.michaelmuther;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Consolidator {

    ArrayList<SourceTrialBalance> sourceTrialBalances;
    ConsolidatedTrialBalance consolidatedTrialBalance;
    LocalDate date;

    public Consolidator(ArrayList<SourceTrialBalance> sourceTrialBalances) {
        this.sourceTrialBalances = sourceTrialBalances;
        this.date = sourceTrialBalances.get(0).getDate();
    }

    public ConsolidatedTrialBalance consolidate(List<SourceTrialBalance> sourceTrialBalances) {
        if (datesMatch() && sourceTrialBalancesAreBalanced())
            return new ConsolidatedTrialBalance(date, consolidateLogic());
        else
            return null;
    }

    private ArrayList<GLAccount> consolidateLogic() {
        // start by adding all accounts
        return null;
    }

    private boolean datesMatch() {
        return sourceTrialBalances.stream().allMatch(i -> i.getDate().equals(date));
    }

    private boolean sourceTrialBalancesAreBalanced() {
        return sourceTrialBalances.stream().allMatch(AbstractTrialBalance::isBalanced);
    }

    public ConsolidatedTrialBalance getConsolidatedTrialBalance() {
        return consolidatedTrialBalance;
    }
}
