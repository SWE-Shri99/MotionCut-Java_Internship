import java.util.Random;
import java.util.Scanner;

public class FakeJobTitleGenerator {

    public static void main(String[] args) {
        // Step 1: Define arrays of adjectives and job titles
        String[] adjectives = {
                "Quantum", "Digital", "Agile", "Synergistic", "Dynamic",
                "Innovative", "Virtual", "Strategic", "Futuristic", "Automated",
                "Hyper-Optimized", "Decentralized", "Scalable", "Smart", "Cloud-Based"
        };

        String[] jobTitles = {
                "Marketing Ninja", "Growth Hacker", "Synergy Officer", "Operations Wizard",
                "Engagement Engineer", "Data Alchemist", "Customer Evangelist", "Brand Guru",
                "Cloud Whisperer", "Tech Magician", "UX Prophet", "AI Whisperer",
                "Blockchain Consultant", "Crypto Evangelist", "Head of Innovation"
        };

        // Step 2: Create Scanner and Random objects
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String userChoice;

        // Step 3: Welcome message
        System.out.println("=========================================");
        System.out.println("   WELCOME TO THE FAKE JOB TITLE GENERATOR");
        System.out.println("=========================================");

        // Step 5: Repeat generation based on user choice
        do {
            // Step 2: Generate random indices
            int adjIndex = random.nextInt(adjectives.length);
            int titleIndex = random.nextInt(jobTitles.length);

            // Step 3: Combine into fake title
            String fakeJobTitle = adjectives[adjIndex] + " " + jobTitles[titleIndex];

            // Step 4: Display the result
            System.out.println("\n🔥 Your Fake Job Title: " + fakeJobTitle);

            // Ask user if they want another
            System.out.print("\nWould you like to generate another? (yes/no): ");
            userChoice = scanner.nextLine().trim().toLowerCase();

            // Handle invalid input
            while (!userChoice.equals("yes") && !userChoice.equals("no") &&
                    !userChoice.equals("y") && !userChoice.equals("n")) {
                System.out.print("Please enter 'yes' or 'no': ");
                userChoice = scanner.nextLine().trim().toLowerCase();
            }

        } while (userChoice.equals("yes") || userChoice.equals("y"));

        // Exit message
        System.out.println("\nThanks for using the Fake Job Title Generator. Stay creative and quirky!");
        scanner.close();
    }
}