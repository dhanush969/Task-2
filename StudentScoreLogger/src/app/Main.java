package app;

import model.StudentRecord;
import service.FileHandler;
import service.ScoreWriterTask;

public class Main {
    public static void main(String[] args) {

        String targetFile = "student_scores.csv";
        FileHandler fileHandler = new FileHandler(targetFile);

        Runnable taskOne = new ScoreWriterTask(fileHandler, new StudentRecord("Darshan", 101, 85));
        Runnable taskTwo = new ScoreWriterTask(fileHandler, new StudentRecord("Aarav", 102, 90));
        Runnable taskThree = new ScoreWriterTask(fileHandler, new StudentRecord("Meera", 103, 95));

        Thread writer1 = new Thread(taskOne);
        Thread writer2 = new Thread(taskTwo);
        Thread writer3 = new Thread(taskThree);

        writer1.start();
        writer2.start();
        writer3.start();

        try {
            writer1.join();
            writer2.join();
            writer3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread interrupted while waiting: " + e.getMessage());
        }

        System.out.println("All scores logged successfully.");
    }
}
