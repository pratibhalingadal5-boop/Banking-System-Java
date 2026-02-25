package banking;
import java.io.Serializable;

public class Account implements Serializable {
    private int accountNumber;
    private String name;
    private double balance;

    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    @Override
    public String toString() {
        return accountNumber + "," + name + "," + balance;
    }

    public static Account fromString(String line) {
        String[] data = line.split(",");
        return new Account(
                Integer.parseInt(data[0]),
                data[1],
                Double.parseDouble(data[2])
        );
    }
}