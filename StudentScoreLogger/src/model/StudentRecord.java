package model;

public class StudentRecord {
    private String studentName;
    private int studentId;
    private double score;

    public StudentRecord(String studentName, int studentId, double score) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.score = score;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentId() {
        return studentId;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return studentName + "," + studentId + "," + (int) score;
    }
}
