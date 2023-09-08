package com.michaelmuther.input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

/*
Class contains folder path data, gathers all files in the path, adds to a hashset, and makes the hashSet of files
publicly available.
 */
public class FolderInput {

    HashSet<File> files = new HashSet<>();

    String INPUT_TRIAL_BALANCE_FOLDER = "src/input_trial_balances";
    Path directoryPath = Paths.get(INPUT_TRIAL_BALANCE_FOLDER);

    // temp helper function
    public void printAllFilesInFolder() {
        try {
            Files.list(directoryPath)
                    .filter(FolderInput::isXLSX) // this picks up the .DS_Store file; it needs to be filtered. regex?
                    .forEach(file -> System.out.println(file.getFileName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // regex predicate
    private static boolean isXLSX(Path path) {
        String FILE_EXTENSION = ".xlsx";
        String fileName = path.getFileName().toString();
        String subString = fileName.substring(fileName.length() - 5,fileName.length());
//        System.out.println(subString);
        return FILE_EXTENSION.equals(subString);
    }

    // temp helper function
    public void getInput() {
        try {
            Files.list(directoryPath)
                    .filter(FolderInput::isXLSX)
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
}
