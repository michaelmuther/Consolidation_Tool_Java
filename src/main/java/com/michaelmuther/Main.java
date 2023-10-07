package com.michaelmuther;

import com.michaelmuther.input.FolderInput;

public class Main {

    // test update 23.09.22

    public static void main(String[] args) {

        System.out.println("\n***testInput***");
        var testInput = new FolderInput();
        System.out.println("All files in the folder before import:");
        testInput.printAllFilesInFolder();
        testInput.getInput();
        System.out.println("All files imported:");
        testInput.printFiles();

//        System.out.println("\n***testSTBFactory***");
//        var testSTBFactory = new STBFactory(testInput.getFiles());
//        testSTBFactory.createSourceTrialBalances();
//        System.out.println("size of sourceTrialBalancesHashSet: "+ testSTBFactory.getSourceTrialBalances().size());
//        testSTBFactory.printAllSourceTrialBalances();



//        testSTBFactory.printAllSourceTrialBalances();
//        System.out.println("number of files: "+ test.getFiles().size());
//        test.getInput();
//        var testConsolidator = new Consolidator(testSTBFactory.getSourceTrialBalances());
//        var testConsolidatedTB = testConsolidator.consolidate();
//        testConsolidatedTB.printTrialBalance();

        // from inputMain:
//        test.printAllFiles();

        // from inputMain:
//        for (SourceTrialBalance i : test.getTrialBalances()) {
//            i.printTrialBalance();
//        }

    }
}

//        HashMap<Integer, GLAccount> us01 = new HashMap<>();
//        us01.put(100, new GLAccount(100, "Cash", BigDecimal.valueOf(1000)));
//        us01.put(110, new GLAccount(110, "Accounts Receivable", BigDecimal.valueOf(20000)));
//        us01.put(120, new GLAccount(120, "Merchandise Inventory", BigDecimal.valueOf(15000)));
//        us01.put(210, new GLAccount(210, "Accounts Payable", BigDecimal.valueOf(-15500)));
//        us01.put(300, new GLAccount(300, "Common Stock", BigDecimal.valueOf(-10000)));
//        us01.put(330, new GLAccount(330, "Retained Earnings", BigDecimal.valueOf(-2000)));
//        us01.put(400, new GLAccount(400, "Sales", BigDecimal.valueOf(-25000)));
//        us01.put(500, new GLAccount(500, "Cost of Goods Sold", BigDecimal.valueOf(15000)));
//        us01.put(510, new GLAccount(510, "Salaries Expense", BigDecimal.valueOf(500)));
//        us01.put(570, new GLAccount(570, "Rent Expense", BigDecimal.valueOf(1000)));
//
//        HashMap<Integer, GLAccount> us02 = new HashMap<>();
//        us02.put(100, new GLAccount(100, "Cash", BigDecimal.valueOf(1000)));
//        us02.put(110, new GLAccount(110, "Accounts Receivable", BigDecimal.valueOf(20000)));
//        us02.put(120, new GLAccount(120, "Merchandise Inventory", BigDecimal.valueOf(15000)));
//        us02.put(210, new GLAccount(210, "Accounts Payable", BigDecimal.valueOf(-15500)));
//        us02.put(300, new GLAccount(300, "Common Stock", BigDecimal.valueOf(-10000)));
//        us02.put(330, new GLAccount(330, "Retained Earnings", BigDecimal.valueOf(-2000)));
//        us02.put(400, new GLAccount(400, "Sales", BigDecimal.valueOf(-25000)));
//        us02.put(500, new GLAccount(500, "Cost of Goods Sold", BigDecimal.valueOf(15000)));
//        us02.put(510, new GLAccount(510, "Salaries Expense", BigDecimal.valueOf(500)));
//        us02.put(570, new GLAccount(570, "Rent Expense", BigDecimal.valueOf(1000)));
//
//        var sourceTBUS01 = new SourceTrialBalance("US01", LocalDate.now(), us01);
//        var sourceTBUS02 = new SourceTrialBalance("US02", LocalDate.now(), us02);
//
//        sourceTBUS01.printTrialBalance();
//        sourceTBUS02.printTrialBalance();
//
//        var sourceTBs = new HashSet<SourceTrialBalance>();
//        sourceTBs.add(sourceTBUS01);
//        sourceTBs.add(sourceTBUS02);

//        var testConsolidator = new Consolidator(sourceTBs);
//        var testConsolidatedTB = testConsolidator.consolidate();
//        testConsolidatedTB.printTrialBalance();