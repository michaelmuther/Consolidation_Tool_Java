package com.michaelmuther.consolidator;

import com.michaelmuther.trialbalance.AbstractTrialBalance;
import com.michaelmuther.trialbalance.ConsolidatedTrialBalance;
import com.michaelmuther.trialbalance.GLAccount;
import com.michaelmuther.trialbalance.SourceTrialBalance;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

public class Consolidator {

    HashSet<SourceTrialBalance> sourceTrialBalances;
    ConsolidatedTrialBalance consolidatedTrialBalance;
    LocalDate date;

    public Consolidator(HashSet<SourceTrialBalance> sourceTrialBalances) {
        this.sourceTrialBalances = sourceTrialBalances;
        this.date = sourceTrialBalances.iterator().next().getDate(); // takes the date of the first STB, this smells a bit
    }

    public ConsolidatedTrialBalance consolidate() {

        // checks this first:
//        System.out.println("dates match: " + datesMatch()); // for testing

        // then checks if the trial balances are balanced. This could be done in the STB factory
//        System.out.println("trial balances are balanced: " + sourceTrialBalancesAreBalanced()); // for testing

        if (datesMatch() && sourceTrialBalancesAreBalanced())
            return new ConsolidatedTrialBalance(date, consolidateLogic());
        else
            return null;
    }

    private HashMap<Integer, GLAccount> consolidateLogic() {

        // declare and instantiate iterator for the sourceTrialBalances HashSet
        var iterator = sourceTrialBalances.iterator();

        // set the consolidated accounts HashMap equal to the first sourceTrialBalance accounts in the HashSet of
        // sourceTrialBalances
        HashMap<Integer, GLAccount> consolidatedAccounts = iterator.next().getAccounts();

        // iterate over the remaining sourceTrialBalances
        while (iterator.hasNext()) {

            // iterate over the HashMap of GLAccounts in each sourceTrialBalance
            iterator.next().getAccounts().values().forEach(account -> {
                var sourceTBAccountNumber = account.getNumber();
                var sourceTBGLAccountBalance = account.getBalance();
                var consolidatedGLAccount = consolidatedAccounts.get(sourceTBAccountNumber);
                var consolidatedAccountBalance = consolidatedGLAccount.getBalance();
                consolidatedGLAccount.setBalance(consolidatedAccountBalance.add(sourceTBGLAccountBalance));
            });
        }

        return consolidatedAccounts;
    }

    private boolean datesMatch() {
        return sourceTrialBalances.stream().allMatch(i -> i.getDate().equals(date));
    }

    private boolean sourceTrialBalancesAreBalanced() {
//        return true;
        return sourceTrialBalances.stream().allMatch(AbstractTrialBalance::isBalanced);
    }

    public ConsolidatedTrialBalance getConsolidatedTrialBalance() {
        return consolidatedTrialBalance;
    }
}
