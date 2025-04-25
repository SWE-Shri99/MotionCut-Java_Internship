import java.util.*;

public class TypingSpeedTest {

    static String[] sentences = {
            "The quick brown fox jumps over the lazy dog.",
            "Java is a high level programming language.",
            "Typing speed tests are fun and useful.",
            "Practice makes a person perfect in coding.",
            "Always write clean and readable code."
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            String sentence = getRandomSentence();
            System.out.println("\n📜 Type the following sentence:");
            System.out.println("\n" + sentence);
            System.out.println("\n⌨️ Start typing and press ENTER when you're done:\n");

            long startTime = System.currentTimeMillis();
            String userInput = scanner.nextLine();
            long endTime = System.currentTimeMillis();

            long duration = endTime - startTime; // in milliseconds
            double timeInSeconds = duration / 1000.0;

            int wpm = calculateWPM(userInput, timeInSeconds);
            double accuracy = calculateAccuracy(sentence, userInput);

            System.out.println("\n⏱ Time Taken: " + timeInSeconds + " seconds");
            System.out.println("📈 Typing Speed: " + wpm + " WPM");
            System.out.println("🎯 Accuracy: " + String.format("%.2f", accuracy) + " %");

            System.out.println("\n🔁 Do you want to try again? (yes/no)");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("\n👋 Thank you for using Typing Speed Test. Goodbye!");
        scanner.close();
    }

    // Function to get a random sentence
    public static String getRandomSentence() {
        Random rand = new Random();
        int index = rand.nextInt(sentences.length);
        return sentences[index];
    }

    // Function to calculate Words Per Minute (WPM)
    public static int calculateWPM(String input, double timeInSeconds) {
        int wordCount = input.trim().split("\\s+").length;
        return (int) ((wordCount * 60) / timeInSeconds);
    }

    // Function to calculate accuracy percentage
    public static double calculateAccuracy(String original, String typed) {
        String[] originalWords = original.trim().split("\\s+");
        String[] typedWords = typed.trim().split("\\s+");

        int correctWords = 0;
        int len = Math.min(originalWords.length, typedWords.length);

        for (int i = 0; i < len; i++) {
            if (originalWords[i].equals(typedWords[i])) {
                correctWords++;
            }
        }

        return ((double) correctWords / originalWords.length) * 100;
    }
}