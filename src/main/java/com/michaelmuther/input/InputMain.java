package com.michaelmuther.input;

import com.michaelmuther.Consolidator;
import com.michaelmuther.GLAccount;
import com.michaelmuther.SourceTrialBalance;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

        String INPUT_TRIAL_BALANCE_FOLDER = "src/input_trial_balances";
        Path directoryPath = Paths.get(INPUT_TRIAL_BALANCE_FOLDER);

        Files.list(directoryPath) // this needs to be in a try block
                .filter(Files::isRegularFile)
                .forEach(file -> System.out.println("File: " + file.getFileName()));

        FileInputStream file = new FileInputStream("src/input_trial_balances/TESTUS01.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file); // this needs to be in a try block
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

        trialBalances.add(new SourceTrialBalance(company, date, accounts));

//        var testConsolidator = new Consolidator(trialBalances);
//        var testConsolidatedTB = testConsolidator.consolidate();
//        testConsolidatedTB.printTrialBalance();
    }
}
