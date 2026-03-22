import java.io.*;
import java.util.*;

class ExpenseManager {
    ArrayList<Expense> expenses = new ArrayList<>();
    final String FILE_NAME = "expenses.json";

    void addExpense(String title, double amount, String category) {
        expenses.add(new Expense(title, amount, category));
        System.out.println("Expense Added!");
    }

    void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        for (Expense e : expenses) {
            System.out.println("Title: " + e.title +
                               ", Amount: " + e.amount +
                               ", Category: " + e.category);
        }
    }

    void totalExpense() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.amount;
        }
        System.out.println("Total Expense: " + total + " Rs");
    }

    // Save as JSON
    void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write("[\n");
            for (int i = 0; i < expenses.size(); i++) {
                Expense e = expenses.get(i);
                bw.write("  {\"title\":\"" + e.title + "\", " +
                         "\"amount\":" + e.amount + ", " +
                         "\"category\":\"" + e.category + "\"}");

                if (i != expenses.size() - 1) {
                    bw.write(",");
                }
                bw.newLine();
            }
            bw.write("]");
            System.out.println("Saved successfully (JSON)!");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // Load from JSON
    void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.startsWith("{")) {
                    line = line.replace("{", "")
                               .replace("}", "")
                               .replace("\"", "");

                    String[] parts = line.split(",");

                    String title = parts[0].split(":")[1];
                    double amount = Double.parseDouble(parts[1].split(":")[1]);
                    String category = parts[2].split(":")[1];

                    expenses.add(new Expense(title, amount, category));
                }
            }
        } catch (IOException e) {
            System.out.println("No previous JSON data found.");
        }
    }
}