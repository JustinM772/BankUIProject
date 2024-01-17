public class Customer {
    private String name;
    private int PIN;
    public Customer(String name, int PIN) {
        this.name = name;
        this.PIN = PIN;
    }
    public int getPIN() {
        return PIN;
    }
    public void updatePIN(int newPIN) {
        PIN = newPIN;
    }
    // uses methods to control the PIN
}
