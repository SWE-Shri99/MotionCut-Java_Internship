import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExpenseTracker {
    private static final String USERS_FILE = "users.ser";
    private static final String EXPENSES_FILE_PREFIX = "expenses_";

    private Map<String, User> users = new HashMap<>();
    private List<Expense> expenses = new ArrayList<>();
    private User currentUser;

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.start();
    }

    public void start() {
        loadUsers();
        Scanner sc = new Scanner(System.in);
        System.out.println("----- Expense Tracker -----");

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) register(sc);
            else if (choice == 2 && login(sc)) break;
            else if (choice == 3) return;
            else System.out.println("Invalid option.");
        }

        loadExpenses();

        while (true) {
            System.out.println("\n1. Add Expense\n2. View Expenses\n3. View by Category\n4. Save & Logout");
            System.out.print("Choose option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> addExpense(sc);
                case 2 -> viewExpenses();
                case 3 -> categoryWiseSummary();
                case 4 -> {
                    saveExpenses();
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void register(Scanner sc) {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
            return;
        }
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User newUser = new User(username, password);
        users.put(username, newUser);
        saveUsers();
        System.out.println("Registration successful.");
    }

    private boolean login(Scanner sc) {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = users.get(username);
        if (user != null && user.validatePassword(password)) {
            currentUser = user;
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }

    private void addExpense(Scanner sc) {
        try {
            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(sc.nextLine());
            System.out.print("Enter category: ");
            String category = sc.nextLine();
            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            expenses.add(new Expense(date, category, amount));
            System.out.println("Expense added.");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("\nDate | Category | Amount");
        System.out.println("---------------------------");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    private void categoryWiseSummary() {
        Map<String, Double> categoryMap = new HashMap<>();
        for (Expense e : expenses) {
            categoryMap.put(e.getCategory(),
                    categoryMap.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
        }

        System.out.println("\nCategory-wise Total:");
        for (Map.Entry<String, Double> entry : categoryMap.entrySet()) {
            System.out.println(entry.getKey() + ": ₹" + entry.getValue());
        }
    }

    private void saveUsers() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            out.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users.");
        }
    }

    private void loadUsers() {
        File file = new File(USERS_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            users = (HashMap<String, User>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users.");
        }
    }

    private void saveExpenses() {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(EXPENSES_FILE_PREFIX + currentUser.getUsername() + ".ser"))) {
            out.writeObject(expenses);
            System.out.println("Expenses saved.");
        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }

    private void loadExpenses() {
        String filename = EXPENSES_FILE_PREFIX + currentUser.getUsername() + ".ser";
        File file = new File(filename);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            expenses = (List<Expense>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading expenses.");
        }
    }
}