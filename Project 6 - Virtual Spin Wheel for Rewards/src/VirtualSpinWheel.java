import java.util.Random;
import java.util.Scanner;

public class VirtualSpinWheel {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Weighted rewards (repeated entries = higher probability)
        String[] rewards = {
                "Free Coffee ☕", "Free Coffee ☕", "Free Coffee ☕",
                "Extra Credit ✨", "Extra Credit ✨",
                "Amazon Gift Card 🎁",
                "Try Again ❌", "Try Again ❌", "Try Again ❌", "Try Again ❌",
                "Movie Ticket 🎬",
                "Discount Coupon 💸", "Discount Coupon 💸"
        };

        int spinLimit = 5;   // Max spins per session
        int spinsDone = 0;   // Counter for spins
        int rewardsWon = 0;  // Counter for successful rewards

        System.out.println("🎉 Welcome to the Virtual Spin Wheel! 🎉");
        System.out.println("Instructions:");
        System.out.println("- Press Enter to spin the wheel.");
        System.out.println("- You can spin up to " + spinLimit + " times.");
        System.out.println("- Try your luck and see what reward you get!");
        System.out.println();

        boolean playAgain = true;

        while (playAgain && spinsDone < spinLimit) {
            System.out.print("👉 Press Enter to Spin...");
            scanner.nextLine(); // Wait for Enter

            // Spin animation
            System.out.print("🎡 Spinning");
            for (int i = 0; i < 5; i++) {
                System.out.print(".");
                Thread.sleep(500); // half-second delay
            }
            System.out.println();

            // Pick random reward
            int index = random.nextInt(rewards.length);
            String selectedReward = rewards[index];
            System.out.println("✨ The wheel stops at: " + selectedReward);

            // Track rewards (ignore "Try Again ❌")
            if (!selectedReward.equals("Try Again ❌")) {
                rewardsWon++;
            }

            spinsDone++;

            // Check spin limit
            if (spinsDone >= spinLimit) {
                System.out.println("\n⚠️ You've reached the spin limit of " + spinLimit + "!");
                break;
            }

            // Ask to play again
            System.out.print("Do you want to spin again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (!response.equals("yes")) {
                playAgain = false;
            }
            System.out.println();
        }

        // Game summary
        System.out.println("\n🙏 Thank you for playing the Virtual Spin Wheel!");
        System.out.println("📊 Session Summary:");
        System.out.println(" - Total spins: " + spinsDone);
        System.out.println(" - Rewards won: " + rewardsWon);
        System.out.println("🎯 Better luck next time if you didn’t win big!");
        System.out.println("Goodbye 👋");

        scanner.close();
    }
}