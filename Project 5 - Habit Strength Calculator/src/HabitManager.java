import java.io.*;
import java.util.*;

public class HabitManager {
    private List<Habit> habits = new ArrayList<>();
    private final String FILE_NAME = "habits.txt";

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public void markHabit(String habitName, boolean completed) {
        for (Habit h : habits) {
            if (h.getName().equalsIgnoreCase(habitName)) {
                h.markCompletion(completed);
                return;
            }
        }
        System.out.println("Habit not found!");
    }

    public void showAllHabits() {
        for (Habit h : habits) h.showStatus();
    }

    public void saveHabits() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Habit h : habits) {
                writer.write(h.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving habits: " + e.getMessage());
        }
    }

    public void loadHabits() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                habits.add(Habit.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading habits: " + e.getMessage());
        }
    }
}