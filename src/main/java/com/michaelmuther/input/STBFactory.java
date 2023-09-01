package com.michaelmuther.input;

import com.michaelmuther.GLAccount;
import com.michaelmuther.SourceTrialBalance;
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
Class takes a hashset of files and makes available a hashset of SourceTrialBalances.
Logic for Excel file conversion is located here. If the excel file trial balance changes, the logic in this class
will need to change.
 */
public class STBFactory {

    String company;
    LocalDate date;
    HashSet<SourceTrialBalance> sourceTrialBalances = new HashSet<>();

    public void

    public void createSourceTrialBalanceFromFile(File file) {
        HashMap<Integer, GLAccount> accounts = new HashMap<>();
        XSSFWorkbook workbook = null;
        try {
//            FileInputStream fileInputStream = new FileInputStream("src/input_trial_balances/TESTUS01.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
            System.out.println("TESTES");
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

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int number = (int) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            BigDecimal balance = BigDecimal.valueOf(row.getCell(2).getNumericCellValue());
//            accounts.put(number, new GLAccount(number, name, balance));
        }

        sourceTrialBalances.add(new SourceTrialBalance(company, date, accounts));
    }

    public HashSet<SourceTrialBalance> getSourceTrialBalances() {
        return sourceTrialBalances;
    }

    public void printAllSourceTrialBalances() {
        sourceTrialBalances.forEach(i -> i.printTrialBalance());
    }
}
