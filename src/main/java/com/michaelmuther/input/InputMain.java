package com.michaelmuther.input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.regex.Pattern;

public class InputMain {

//    HashSet<SourceTrialBalance> trialBalances = new HashSet<>();
    HashSet<File> files = new HashSet<>();

    String company;
    LocalDate date;
    String INPUT_TRIAL_BALANCE_FOLDER = "src/input_trial_balances";
    Path directoryPath = Paths.get(INPUT_TRIAL_BALANCE_FOLDER);

    Pattern pattern = Pattern.compile(".XLSX");
//    Matcher matcher = pattern.matcher();

    // temp helper function
    public void printAllFilesInFolder() {
        try {
            Files.list(directoryPath)
                    .filter(InputMain::isXLSX) // this picks up the .DS_Store file; it needs to be filtered. regex?
                    .forEach(file -> System.out.println(file.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // regex predicate
    private static boolean isXLSX(Path path) {
        String FILE_EXTENSION = "xlsx";
        String fileName = path.getFileName().toString();
        String subString = fileName.substring(fileName.length() - 4,fileName.length());
//        System.out.println(subString);
        return FILE_EXTENSION.equals(subString);
    }

    // temp helper function
    public void getInput() {
        try {
            Files.list(directoryPath)
                    .filter(InputMain::isXLSX)
                    .forEach(file -> files.add(file.toFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<File> getFiles() {
        return files;
    }

    public void printFiles() {
        files.forEach(System.out::println);
    }

//    private void addTrialBalances(File file) {
//        HashMap<Integer, GLAccount> accounts = new HashMap<>();
//        XSSFWorkbook workbook = null;
//        try {
////            FileInputStream fileInputStream = new FileInputStream("src/input_trial_balances/TESTUS01.xlsx");
//            FileInputStream fileInputStream = new FileInputStream(file);
//            workbook = new XSSFWorkbook(fileInputStream);
//            System.out.println("TESTES");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        XSSFSheet sheet = workbook.getSheetAt(0);
//        Row headerRow = sheet.getRow(0);
//
//        company = headerRow.getCell(1).getStringCellValue();
//        date = headerRow.getCell(3).getLocalDateTimeCellValue().toLocalDate();
//
//        System.out.println(company);
//        System.out.println(date);
//
//        Iterator<Row> rowIterator = sheet.iterator();
//        rowIterator.next(); // skip first row (0)
//        rowIterator.next(); // skip second row (1)
//
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//            int number = (int) row.getCell(0).getNumericCellValue();
//            String name = row.getCell(1).getStringCellValue();
//            BigDecimal balance = BigDecimal.valueOf(row.getCell(2).getNumericCellValue());
////            accounts.put(number, new GLAccount(number, name, balance));
//        }
//
//        trialBalances.add(new SourceTrialBalance(company, date, accounts));
//    }
//
//    public HashSet<SourceTrialBalance> getTrialBalances() {
//        return trialBalances;
//    }

}
