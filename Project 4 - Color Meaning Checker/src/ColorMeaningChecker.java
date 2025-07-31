import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ColorMeaningChecker {
    public static void main(String[] args) {
        // Step 1: Create a HashMap to store color meanings
        Map<String, String> colorMeanings = new HashMap<>();

        // Predefined colors and their meanings
        colorMeanings.put("Red", "Symbolizes passion, energy, and love.");
        colorMeanings.put("Blue", "Represents calmness, trust, and loyalty.");
        colorMeanings.put("Green", "Represents nature, growth, and harmony.");
        colorMeanings.put("Yellow", "Symbolizes happiness, optimism, and warmth.");
        colorMeanings.put("Black", "Represents elegance, mystery, and power.");
        colorMeanings.put("White", "Symbolizes purity, peace, and innocence.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Color Meaning Checker ---");
            System.out.println("1. Check color meaning");
            System.out.println("2. Add a new color");
            System.out.println("3. Show all available colors");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Check color meaning
                    System.out.print("Enter the color name: ");
                    String colorName = scanner.nextLine().trim();

                    if (colorName.isEmpty()) {
                        System.out.println("Invalid input! Please enter a color name.");
                    } else {
                        String meaning = colorMeanings.get(capitalize(colorName));
                        if (meaning != null) {
                            System.out.println("Meaning of " + capitalize(colorName) + ": " + meaning);
                        } else {
                            System.out.println("Color not found. Try again or add it.");
                        }
                    }
                    break;

                case "2":
                    // Add a new color
                    System.out.print("Enter the new color name: ");
                    String newColor = scanner.nextLine().trim();

                    if (newColor.isEmpty()) {
                        System.out.println("Invalid input! Color name cannot be empty.");
                        break;
                    }

                    if (colorMeanings.containsKey(capitalize(newColor))) {
                        System.out.println("Color already exists in the list.");
                    } else {
                        System.out.print("Enter the meaning for " + capitalize(newColor) + ": ");
                        String newMeaning = scanner.nextLine().trim();
                        if (newMeaning.isEmpty()) {
                            System.out.println("Meaning cannot be empty.");
                        } else {
                            colorMeanings.put(capitalize(newColor), newMeaning);
                            System.out.println("New color added successfully!");
                        }
                    }
                    break;

                case "3":
                    // Show all colors
                    System.out.println("Available colors: " + colorMeanings.keySet());
                    break;

                case "4":
                    // Exit program
                    System.out.println("Exiting... Thank you for using Color Meaning Checker!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select from 1 to 4.");
            }
        }
    }

    // Helper method to capitalize first letter
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}