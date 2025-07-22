import java.util.HashMap;
import java.util.Scanner;

public class EmojiMoodResponder {

    public static void main(String[] args) {
        // Step 1: Initialize mood-to-emoji+message map
        HashMap<String, String> moodResponses = new HashMap<>();
        moodResponses.put("happy", "😊 Stay smiling! Your happiness is contagious!");
        moodResponses.put("sad", "😢 It's okay to feel down sometimes. Better days are ahead!");
        moodResponses.put("tired", "😴 Take some rest, you've earned it.");
        moodResponses.put("angry", "😠 Breathe in... breathe out. Let it go.");
        moodResponses.put("excited", "🤩 That energy is amazing! Keep it up!");
        moodResponses.put("bored", "😐 Find something fun to do. Maybe try a new hobby?");
        moodResponses.put("anxious", "😰 You got this. One step at a time.");

        Scanner scanner = new Scanner(System.in);

        System.out.println("🎉 Welcome to the Emoji Mood Responder!");
        System.out.println("----------------------------------------");

        while (true) {
            // Step 2: Ask user for their mood
            System.out.print("\nHow are you feeling right now? (Type 'exit' to quit): ");
            String userInput = scanner.nextLine().toLowerCase().trim();

            // Step 5: Handle exit condition
            if (userInput.equals("exit")) {
                System.out.println("👋 Goodbye! Stay positive!");
                break;
            }

            // Step 3 & 4: Check and respond
            if (moodResponses.containsKey(userInput)) {
                System.out.println("Response: " + moodResponses.get(userInput));
            } else {
                System.out.println("🤔 Hmm... I don't recognize that mood.");
                System.out.println("Try one of these: " + moodResponses.keySet());
            }
        }

        scanner.close();
    }
}