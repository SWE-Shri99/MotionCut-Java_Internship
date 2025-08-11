import java.io.IOException;
import java.util.Scanner;

public class HabitStrengthCalculator {
    static final String FILE_NAME = "habit.txt";

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Habit habit = HabitStorage.loadHabit(FILE_NAME);

        if (habit == null) {
            System.out.print("Enter habit name: ");
            String name = sc.nextLine();
            System.out.print("Enter habit description: ");
            String desc = sc.nextLine();
            System.out.print("Enter frequency (Daily/Weekly): ");
            String freq = sc.nextLine();
            habit = new Habit(name, desc, freq);
        } else {
            System.out.println("Loaded habit: " + habit.name);
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- Habit Strength Calculator ---");
            System.out.println("1. Mark Habit Completion");
            System.out.println("2. View Habit Strength & Feedback");
            System.out.println("3. Save & Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Did you complete the habit today? (yes/no): ");
                    boolean completed = sc.nextLine().trim().equalsIgnoreCase("yes");
                    habit.markCompletion(completed);
                    System.out.println("Recorded!");
                    break;
                case 2:
                    System.out.printf("Habit: %s\nDescription: %s\nStrength: %.2f%%\n",
                            habit.name, habit.description, habit.getStrength());
                    System.out.println("Feedback: " + habit.getFeedback());
                    System.out.println("Current Streak: " + habit.streak + " days");
                    break;
                case 3:
                    HabitStorage.saveHabit(habit, FILE_NAME);
                    running = false;
                    System.out.println("Habit saved. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}