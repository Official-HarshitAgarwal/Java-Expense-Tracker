class Expense {
    String title;
    double amount;
    String category;

    Expense(String title, double amount, String category) {
        this.title = title;
        this.amount = amount;
        this.category = category;
    }

    public String toString() {
        return title + "," + amount + "," + category;
    }

    public static Expense fromString(String data) {
        String[] parts = data.split(",");
        return new Expense(parts[0], Double.parseDouble(parts[1]), parts[2]);
    }
}