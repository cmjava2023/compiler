package cmjava2023.controlFlow.if_test;

public class Main {
    public static void main(String[] args) {
        boolean isMoving = true;
        int currentSpeed = 5;

        if (isMoving) {
            currentSpeed--;
        }

        isMoving = false;

        if (isMoving) {
            currentSpeed--;
        } else {
            System.out.println("The bicycle has already stopped!");
        }

        int testscore = 76;
        char grade;

        if (testscore >= 90) {
            grade = 'A';
        } else if (testscore >= 80) {
            grade = 'B';
        } else if (testscore >= 70) {
            grade = 'C';
        } else if (testscore >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println(grade);
    }
}