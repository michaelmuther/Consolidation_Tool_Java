package com.michaelmuther.input;

import com.michaelmuther.Consolidator;
import com.michaelmuther.GLAccount;
import com.michaelmuther.SourceTrialBalance;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class InputMain {

    public static void main(String[] args) {

        HashSet<SourceTrialBalance> trialBalances = new HashSet<>();
        HashMap<Integer, GLAccount> accounts = new HashMap<>();
        String company;
        LocalDate date;

        String input_trial_balancesFolderPath = "src/input_trial_balances";
        Path directoryPath = Paths.get(input_trial_balancesFolderPath);

        try {
            Files.list(directoryPath)
                    .filter(Files::isRegularFile)
                    .forEach(file -> System.out.println("File: " + file.getFileName()));

            FileInputStream file = new FileInputStream(new File("src/input_trial_balances/TESTUS01.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
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
                accounts.put(number, new GLAccount(number, name, balance));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        trialBalances.add(new SourceTrialBalance(company, date, accounts));



        var testConsolidator = new Consolidator(trialBalances);
        var testConsolidatedTB = testConsolidator.consolidate();
        testConsolidatedTB.printTrialBalance();
    }




}
