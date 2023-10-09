package com.michaelmuther.trialbalance;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/*
Class is passed a hashset of File objects upon instantiation and makes available a hashset of SourceTrialBalances.
Logic for Excel file conversion is located here. If the excel file trial balance changes, the logic in this class
will need to change.
 */
public class STBFactory {

    // ********* FINALIZED CODE BELOW*****************

    private final HashSet<File> files;
    private final HashSet<SourceTrialBalance> sourceTrialBalances = new HashSet<>();

    public STBFactory(HashSet<File> files) {
        this.files = files;
    }

    // ********* FINALIZED CODE ABOVE*****************


    public void createSourceTrialBalances() {

        System.out.println("Amount of files: " + files.size()); // for testing

        for(File file : files) {
            sourceTrialBalances.add(createSourceTrialBalanceFromFile(file));
        }
    }

    private SourceTrialBalance createSourceTrialBalanceFromFile(File file) {

        String company;
        LocalDate date;
        HashMap<Integer, GLAccount> accounts = new HashMap<>();
        XSSFWorkbook workbook = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
            System.out.println("Inside try block");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFSheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);

        company = headerRow.getCell(1).getStringCellValue();
        date = headerRow.getCell(3).getLocalDateTimeCellValue().toLocalDate();

        System.out.println(company);
        System.out.println(date);

        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // skip first row (0)
        rowIterator.next(); // skip second row (1)

        int rowNumber = 3; // starts at 1

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
//            try {
//                System.out.println("company: " + company + " row number: " + rowNumber + " cell 0 (account) type: " + row.getCell(0).getCellType());
//                System.out.println("company: " + company + " row number: " + rowNumber + " cell 1 (name) type: " + row.getCell(1).getCellType());
//                System.out.println("company: " + company + " row number: " + rowNumber + " cell 2 (balance) type: " + row.getCell(2).getCellType());
//            } catch (Exception e) {
//                System.out.println("extra row: company: " + company + " row number: " + rowNumber);
//            } finally {
//                rowNumber++;
//            }

            int number = (int) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            BigDecimal balance = BigDecimal.valueOf(row.getCell(2).getNumericCellValue());
            accounts.put(number, new GLAccount(number, name, balance));
        }

//        return null;
        return new SourceTrialBalance(company, date, accounts);
    }

    // *************** FINALIZED CODE BELOW ******************

    /**
     * getter for sourceTrialBalances
     * @return all source trial balances in a HashSet
     */
    public HashSet<SourceTrialBalance> getSourceTrialBalances() {
        return sourceTrialBalances;
    }

    /**
     * util method to print all SourceTrialBalances
     */
    public void printAllSourceTrialBalances() {
        sourceTrialBalances.forEach(sourceTrialBalance -> sourceTrialBalance.printTrialBalance());
    }
}

/*
Commented code stash:

class members:

in constructor
//        this.createSourceTrialBalances();

 */
