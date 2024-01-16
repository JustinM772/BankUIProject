public class Transaction {
    private String ID;
    private String action;
    private double savingsBalance;
    private double checkingBalance;
    private boolean success;
    public Transaction(String ID, String action, double savingsBalance, double checkingBalance, boolean success) {
        this.ID = ID;
        this.action = action;
        this.savingsBalance = savingsBalance;
        this.checkingBalance = checkingBalance;
        this.success = success;
    }
    public String getID() {
        return ID;
    }
    public String getAction() {
        return action;
    }
    public double getSavingsBalance() {
        return savingsBalance;
    }
    public double getCheckingBalance() {
        return checkingBalance;
    }
    public String getSuccess() {
        if (success) {
            return "Successful";
        } else {
            return "Failed";
        }
    }
}
