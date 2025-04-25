import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Java Link Shortener!");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter the long URL: ");
                    String longUrl = scanner.nextLine();
                    String shortUrl = linkShortener.shortenUrl(longUrl);
                    System.out.println("Shortened URL: " + shortUrl);
                    break;

                case "2":
                    System.out.print("Enter the short URL: ");
                    String inputShort = scanner.nextLine();
                    String original = linkShortener.expandUrl(inputShort);
                    System.out.println("Original URL: " + original);
                    break;

                case "3":
                    System.out.println("Thank you for using the Link Shortener!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}