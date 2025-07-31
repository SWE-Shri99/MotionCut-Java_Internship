import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class DailyGoalReminder {

    // Inner class to store each goal and its reminder time
    static class Goal {
        String task;
        LocalTime reminderTime;

        Goal(String task, LocalTime reminderTime) {
            this.task = task;
            this.reminderTime = reminderTime;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Goal> goals = new ArrayList<>();

        System.out.println("===== Daily Goal Reminder =====");
        System.out.println("Enter your daily goals and reminder times.");
        System.out.println("Type 'done' when finished.\n");

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Step 1: Get goals and times from the user
        while (true) {
            System.out.print("Enter goal/task (or type 'done'): ");
            String task = sc.nextLine().trim();

            if (task.equalsIgnoreCase("done")) {
                break;
            }

            if (task.isEmpty()) {
                System.out.println("❌ Goal cannot be empty!");
                continue;
            }

            LocalTime reminderTime = null;
            while (true) {
                System.out.print("Enter reminder time for '" + task + "' (HH:mm in 24-hour format): ");
                String timeInput = sc.nextLine().trim();

                try {
                    reminderTime = LocalTime.parse(timeInput, timeFormatter);
                    break; // valid time
                } catch (Exception e) {
                    System.out.println("❌ Invalid time format! Please use HH:mm format.");
                }
            }

            goals.add(new Goal(task, reminderTime));
            System.out.println("✅ Goal added: " + task + " at " + reminderTime);
        }

        if (goals.isEmpty()) {
            System.out.println("⚠ No goals entered. Exiting...");
            return;
        }

        // Step 2: Schedule reminders
        System.out.println("\n📅 Scheduling reminders...");
        Timer timer = new Timer();

        for (Goal goal : goals) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime reminderDateTime = LocalDateTime.of(LocalDate.now(), goal.reminderTime);

            // If the time has already passed today, skip scheduling
            if (reminderDateTime.isBefore(now)) {
                System.out.println("⚠ Skipping '" + goal.task + "' because the time has already passed today.");
                continue;
            }

            long delay = Duration.between(now, reminderDateTime).toMillis();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\n🔔 Reminder: " + goal.task + " (" + goal.reminderTime + ")");
                }
            }, delay);

            System.out.println("📌 Reminder set for '" + goal.task + "' at " + goal.reminderTime);
        }

        System.out.println("\n✅ All reminders scheduled. Application will keep running...");
    }
}