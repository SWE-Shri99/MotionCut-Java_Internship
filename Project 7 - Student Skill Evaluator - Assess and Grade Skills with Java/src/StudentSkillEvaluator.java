import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    String id;
    String[] skills = {"Programming", "Communication", "Problem-Solving", "Teamwork", "Creativity"};
    int[] scores = new int[skills.length];
    int total;
    double average;
    char grade;
    String feedback;

    // Calculate total, average, grade, and feedback
    void evaluate() {
        total = 0;
        for (int score : scores) {
            total += score;
        }
        average = (double) total / scores.length;
        grade = calculateGrade(average);
        feedback = generateFeedback(grade);
    }

    private char calculateGrade(double avg) {
        if (avg >= 90) return 'A';
        else if (avg >= 75) return 'B';
        else if (avg >= 60) return 'C';
        else if (avg >= 40) return 'D';
        else return 'F';
    }

    private String generateFeedback(char grade) {
        switch (grade) {
            case 'A': return "Excellent work! Keep it up! 🌟";
            case 'B': return "Good job! You can aim even higher. 👍";
            case 'C': return "Average performance. Keep practicing. 📚";
            case 'D': return "Needs improvement. Work harder. 💪";
            default: return "Fail. Don’t give up – keep trying! 🚀";
        }
    }

    void printReport() {
        System.out.println("\n📊 Student Report:");
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Skill Scores:");
        for (int i = 0; i < skills.length; i++) {
            System.out.println(" - " + skills[i] + ": " + scores[i]);
        }
        System.out.printf("Total: %d | Average: %.2f | Grade: %c%n", total, average, grade);
        System.out.println("Feedback: " + feedback);
    }

    String exportData() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", ID: ").append(id).append("\n");
        sb.append("Scores: ");
        for (int i = 0; i < skills.length; i++) {
            sb.append(skills[i]).append("=").append(scores[i]).append(" ");
        }
        sb.append("\nTotal: ").append(total)
                .append(", Average: ").append(String.format("%.2f", average))
                .append(", Grade: ").append(grade)
                .append("\nFeedback: ").append(feedback)
                .append("\n-----------------------------------\n");
        return sb.toString();
    }
}

public class StudentSkillEvaluator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        boolean addMore = true;

        System.out.println("🎓 Welcome to Student Skill Evaluator 🎓");

        while (addMore) {
            Student student = new Student();

            System.out.print("Enter Student Name: ");
            student.name = scanner.nextLine();

            System.out.print("Enter Student ID: ");
            student.id = scanner.nextLine();

            // Enter scores for skills
            for (int i = 0; i < student.skills.length; i++) {
                System.out.print("Enter marks for " + student.skills[i] + " (0-100): ");
                student.scores[i] = Integer.parseInt(scanner.nextLine());
            }

            student.evaluate();
            student.printReport();

            students.add(student);

            System.out.print("\nDo you want to enter another student? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            addMore = response.equals("yes");
        }

        // Display top performer
        if (!students.isEmpty()) {
            Student top = students.get(0);
            for (Student s : students) {
                if (s.average > top.average) {
                    top = s;
                }
            }
            System.out.println("\n🏆 Top Performer: " + top.name + " (ID: " + top.id + ")");
            System.out.printf("Average Score: %.2f | Grade: %c%n", top.average, top.grade);
        }

        // Export results to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StudentReports.txt"))) {
            for (Student s : students) {
                writer.write(s.exportData());
            }
            System.out.println("\n📂 Reports exported successfully to StudentReports.txt");
        } catch (IOException e) {
            System.out.println("❌ Error saving report: " + e.getMessage());
        }

        System.out.println("\n🙏 Thank you for using Student Skill Evaluator!");
        scanner.close();
    }
}