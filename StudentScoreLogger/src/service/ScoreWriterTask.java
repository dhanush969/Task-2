package service;

import model.StudentRecord;

public class ScoreWriterTask implements Runnable {
    private FileHandler handler;
    private StudentRecord record;

    public ScoreWriterTask(FileHandler handler, StudentRecord record) {
        this.handler = handler;
        this.record = record;
    }

    @Override
    public void run() {
        try {
            handler.saveToFile(record);
            System.out.println("Logged: " + record.toString());
        } catch (Exception ex) {
            System.err.println("Unable to log record for " + record.getStudentName() + ": " + ex.getMessage());
        }
    }
}
