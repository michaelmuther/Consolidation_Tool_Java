package com.michaelmuther.output;

import com.michaelmuther.trialbalance.ConsolidatedTrialBalance;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderOutput {

    ConsolidatedTrialBalance consolidatedTrialBalance;

    String OUTPUT_FOLDER = "src/output_consolidated_trial_balance";
    Path outputFolderPath = Paths.get(OUTPUT_FOLDER);

    public FolderOutput(ConsolidatedTrialBalance consolidatedTrialBalance) {
        this.consolidatedTrialBalance = consolidatedTrialBalance;
    }

    public void generateOutput() {

        // note: XSSF is the .xlsx file; HSSF is the old ext. .xls
        // get current date, append to end of file name
        // get date of consolidation
        // get company of consolidation
        // put file in right folder
        /* Build correct header:
         A               B                 C                   D
        Company Name:	(company name)	    Date:              (date)
        Account #	    Account Description	Account Amount
         */

        Workbook wb = new XSSFWorkbook();
        try (OutputStream fileOut = new FileOutputStream("workbook.xlsx")) {
            wb.write(fileOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
