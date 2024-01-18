public class TransactionHistory {
    private Transaction[] transactions;
    public TransactionHistory() {
        transactions = new Transaction[0];
    }
    public Transaction[] getHistory() {
        return transactions;
    }
    public void addTransaction(Transaction transaction) {
        if (transactions.length == 0) {
            transactions = new Transaction[1]; // has to change length if it's 0 to avoid null errors
            transactions[0] = transaction;
        } else {
            Transaction[] temp = transactions; // uses a temp variable because cannot directly increase list length
            transactions = new Transaction[temp.length + 1];
            for (int i = 0; i < temp.length; i++) {
                transactions[i] = temp[i];
            }
            transactions[temp.length] = transaction;
        }
    }
}


