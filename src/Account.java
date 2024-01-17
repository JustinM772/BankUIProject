public class Account {
    private String name;
    private double balance;
    private Customer customer; // uses objects from other classes
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public void updateBalance(double newBalance) {
        balance = newBalance;
    }
    // methods are used to control balance
}
