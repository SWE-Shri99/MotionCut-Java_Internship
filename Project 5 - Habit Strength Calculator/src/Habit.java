import java.util.*;

public class Habit {
    private String name;
    private String description;
    private String frequency;
    private List<Boolean> completionHistory;

    public Habit(String name, String description, String frequency) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.completionHistory = new ArrayList<>();
    }

    public void markCompletion(boolean completed) {
        completionHistory.add(completed);
    }

    public double calculateStrength() {
        if (completionHistory.isEmpty()) return 0;
        long completed = completionHistory.stream().filter(b -> b).count();
        return (completed * 100.0) / completionHistory.size();
    }

    public String getFeedback() {
        double strength = calculateStrength();
        if (strength >= 80) return "Great job! You're consistently keeping up with your habit.";
        else if (strength >= 50) return "Good job! You're making progress, but there's room for improvement.";
        else return "Keep going! Try to be more consistent in completing your habit.";
    }

    public String toFileString() {
        return name + "," + description + "," + frequency + "," + completionHistory.toString().replaceAll("[\\[\\] ]", "");
    }

    public static Habit fromFileString(String data) {
        String[] parts = data.split(",", 4);
        Habit habit = new Habit(parts[0], parts[1], parts[2]);
        String[] completions = parts[3].split(",");
        for (String c : completions) {
            habit.markCompletion(Boolean.parseBoolean(c));
        }
        return habit;
    }

    public String getName() {
        return name;
    }

    public void showStatus() {
        System.out.printf("Habit: %s\nDescription: %s\nFrequency: %s\nStrength: %.2f%%\n%s\n\n",
                name, description, frequency, calculateStrength(), getFeedback());
    }
}