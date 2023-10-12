package com.michaelmuther.output;

import com.michaelmuther.trialbalance.ConsolidatedTrialBalance;
import com.michaelmuther.trialbalance.GLAccount;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class FolderOutput {

    ConsolidatedTrialBalance consolidatedTrialBalance;

    // put file in right folder DONE
    String OUTPUT_FOLDER = "src/output_consolidated_trial_balance/";

    public FolderOutput(ConsolidatedTrialBalance consolidatedTrialBalance) {
        this.consolidatedTrialBalance = consolidatedTrialBalance;
    }

    public void generateOutput() {

        // get current date, append to end of file name DONE, reformat needed
        String dateTime = LocalTime.now().toString();

        // note: XSSF is the .xlsx file; HSSF is the old ext. .xls

//        // get date of consolidation
        String consolidationDate = consolidatedTrialBalance.getDate().toString();

        // get company of consolidation
        String companyName = consolidatedTrialBalance.getCompanyName();

        /* Build correct header:
         A               B                 C                   D
        Company Name:	(company name)	    Date:              (date)
        Account #	    Account Description	Account Amount
         */

        // create the workbook
        Workbook workBook = new XSSFWorkbook();

        // create the CreationHelper
        CreationHelper creationHelper = workBook.getCreationHelper();

        // create the sheet
        Sheet sheet = workBook.createSheet("consolidated_trial_balance");

        // create the first header row
        Row headerRow1 = sheet.createRow(0);
        headerRow1.createCell(0).setCellValue(creationHelper.createRichTextString("Company Name:"));
        headerRow1.createCell(1).setCellValue(creationHelper.createRichTextString(companyName));
        headerRow1.createCell(2).setCellValue(creationHelper.createRichTextString("Date:"));
        headerRow1.createCell(3).setCellValue(creationHelper.createRichTextString(consolidationDate));

        // create the second header row
        Row headerRow2 = sheet.createRow(1);
        headerRow2.createCell(0).setCellValue(creationHelper.createRichTextString("Account #"));
        headerRow2.createCell(1).setCellValue(creationHelper.createRichTextString("Account Description"));
        headerRow2.createCell(2).setCellValue(creationHelper.createRichTextString("Account Amount"));

        for (Map.Entry<Integer, GLAccount> i : consolidatedTrialBalance.getAccounts().entrySet()) {

        }


        try (OutputStream fileOut = new FileOutputStream(OUTPUT_FOLDER + "TEST" + LocalDateTime.now() + ".xlsx")) {
            workBook.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
