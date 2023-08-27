package com.michaelmuther;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Consolidator {

    HashSet<SourceTrialBalance> sourceTrialBalances;
    ConsolidatedTrialBalance consolidatedTrialBalance;
    LocalDate date;

    public Consolidator(HashSet<SourceTrialBalance> sourceTrialBalances) {
        this.sourceTrialBalances = sourceTrialBalances;
        this.date = sourceTrialBalances.iterator().next().getDate();
    }

    public ConsolidatedTrialBalance consolidate(List<SourceTrialBalance> sourceTrialBalances) {
        if (datesMatch() && sourceTrialBalancesAreBalanced())
            return new ConsolidatedTrialBalance(date, consolidateLogic());
        else
            return null;
    }

    private HashMap<Integer, GLAccount> consolidateLogic() {
        HashMap<Integer, GLAccount> consolidatedAccounts = sourceTrialBalances.iterator().next().getAccounts();
//        for (GLAccount account : consolidatedAccounts) {
//
//        }
        return consolidatedAccounts;
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
