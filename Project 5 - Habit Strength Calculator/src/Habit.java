import java.util.ArrayList;

public class Habit {
    String name;
    String description;
    String frequency;
    ArrayList<Boolean> completionList;
    int streak;

    public Habit(String name, String description, String frequency) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.completionList = new ArrayList<>();
        this.streak = 0;
    }

    public void markCompletion(boolean completed) {
        completionList.add(completed);
        streak = completed ? streak + 1 : 0;
    }

    public double getStrength() {
        if (completionList.isEmpty()) return 0;
        int completedCount = 0;
        for (boolean status : completionList) if (status) completedCount++;
        return ((double) completedCount / completionList.size()) * 100;
    }

    public String getFeedback() {
        double strength = getStrength();
        if (strength >= 80) return "Great job! You're consistently keeping up!";
        else if (strength >= 50) return "Good job! Keep improving!";
        else return "Keep going! Try to be more consistent.";
    }
}