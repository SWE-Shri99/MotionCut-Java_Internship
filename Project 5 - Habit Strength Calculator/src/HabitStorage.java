import java.io.*;

public class HabitStorage {
    public static void saveHabit(Habit habit, String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(habit.name + "\n" + habit.description + "\n" + habit.frequency + "\n" + habit.streak + "\n");
        for (boolean status : habit.completionList) {
            bw.write(status + "\n");
        }
        bw.close();
    }

    public static Habit loadHabit(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) return null;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String name = br.readLine();
        String desc = br.readLine();
        String freq = br.readLine();
        int streak = Integer.parseInt(br.readLine());

        Habit habit = new Habit(name, desc, freq);
        habit.streak = streak;

        String line;
        while ((line = br.readLine()) != null) {
            habit.completionList.add(Boolean.parseBoolean(line));
        }
        br.close();
        return habit;
    }
}