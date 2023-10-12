package com.michaelmuther.input;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Stream;

/*
Class contains folder path data, gathers all files in the path, adds to a hashset, and makes the hashSet of files
publicly available.
 */
public class FolderInput {

    HashSet<File> files = new HashSet<>();

    String INPUT_TRIAL_BALANCE_FOLDER = "src/input_trial_balances";
    Path inputFolderPath = Paths.get(INPUT_TRIAL_BALANCE_FOLDER);

    /**
     * This is a helper method that returns true if the Path object of a file has .xlsx as its suffix; method is used in
     * the printAllFilesInFolder method of this same class.
     * @param path
     * @return boolean
     */
    private static boolean isXLSX(Path path) {
        final String FILE_EXTENSION = ".xlsx";
        String fileName = path.getFileName().toString();
        return fileName.endsWith(FILE_EXTENSION);
    }

    public void getInput() {
        try {
            Files.list(inputFolderPath)
                    .filter(FolderInput::isXLSX)
                    .forEach(path -> files.add(path.toFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashSet<File> getFiles() {
        return files;
    }

    // temp helper function
    public void printFiles() {
        files.forEach(System.out::println);
    }

    // temp helper function
    public void printAllFilesInFolder() {
        try (Stream<Path> pathStream = Files.list(inputFolderPath)){
            pathStream.filter(FolderInput::isXLSX)
                    .map(Path::getFileName)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
