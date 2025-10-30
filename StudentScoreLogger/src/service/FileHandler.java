package service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.StudentRecord;

public class FileHandler {
    private String outputFile;
    private final Object fileLock = new Object();

    public FileHandler(String outputFile) {
        this.outputFile = outputFile;
    }

    // Method to append a student's record to the file safely
    public void saveToFile(StudentRecord student) {
        synchronized (fileLock) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, true))) {
                writer.println(student.toString());
            } catch (IOException e) {
                System.err.println("Failed to write record: " + e.getMessage());
            }
        }
    }
}
