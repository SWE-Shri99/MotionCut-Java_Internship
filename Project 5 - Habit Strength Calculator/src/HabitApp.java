import java.util.*;

public class HabitApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HabitManager manager = new HabitManager();
        manager.loadHabits();

        while (true) {
            System.out.println("\n--- Habit Strength Calculator ---");
            System.out.println("1. Add New Habit");
            System.out.println("2. Mark Habit Completion");
            System.out.println("3. View Habit Status");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter habit name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter frequency (Daily/Weekly): ");
                    String freq = sc.nextLine();
                    manager.addHabit(new Habit(name, desc, freq));
                    System.out.println("Habit added successfully!");
                    break;

                case 2:
                    System.out.print("Enter habit name: ");
                    String hName = sc.nextLine();
                    System.out.print("Did you complete it today? (true/false): ");
                    boolean completed = sc.nextBoolean();
                    manager.markHabit(hName, completed);
                    break;

                case 3:
                    manager.showAllHabits();
                    break;

                case 4:
                    manager.saveHabits();
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}