public class TransactionHistory {
    private Transaction[] transactions;
    public TransactionHistory() {
        transactions = new Transaction[0];
    }
    public void addTransaction(Transaction transaction) {
        if (transactions.length == 0) {
            transactions = new Transaction[1];
            transactions[0] = transaction;
        } else {
            Transaction[] temp = transactions;
            transactions = new Transaction[temp.length + 1];
            for (int i = 0; i < temp.length; i++) {
                transactions[i] = temp[i];
            }
            transactions[temp.length] = transaction;
        }
    }
    public Transaction[] getHistory() {
        return transactions;
    }
}


