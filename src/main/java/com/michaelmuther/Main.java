package com.michaelmuther;

import com.michaelmuther.consolidator.Consolidator;
import com.michaelmuther.input.FolderInput;
import com.michaelmuther.output.FolderOutput;
import com.michaelmuther.trialbalance.STBFactory;

public class Main {

    public static void main(String[] args) {

        System.out.println("\n***testInput***");
        var testInput = new FolderInput();
        System.out.println("All files in the folder before import:");
        testInput.printAllFilesInFolder();
        testInput.getInput();
        System.out.println("All files imported:");
        testInput.printFiles();

        System.out.println("\n***testSTBFactory***");
        var testSTBFactory = new STBFactory(testInput.getFiles());
        testSTBFactory.createSourceTrialBalances();
        System.out.println("size of sourceTrialBalancesHashSet: "+ testSTBFactory.getSourceTrialBalances().size());
        testSTBFactory.printAllSourceTrialBalances(); // for testing

        System.out.println("\n***testConsolidator***");
        var testConsolidator = new Consolidator(testSTBFactory.getSourceTrialBalances());
        var testConsolidatedTB = testConsolidator.consolidate();
        testConsolidatedTB.printTrialBalance(); // for testing

        System.out.println("\n***testOutput***");
        var testOutput = new FolderOutput(testConsolidatedTB);
        testOutput.generateOutput();
    }
}
