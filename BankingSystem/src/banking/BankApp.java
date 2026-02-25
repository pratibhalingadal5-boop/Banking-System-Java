package banking;


	import java.io.*;
	import java.util.*;

	public class BankApp {
	    static final String FILE_NAME = "accounts.txt";
	    static Scanner sc = new Scanner(System.in);

	    public static void main(String[] args) {
	        int choice;
	        do {
	            System.out.println("\n--- Banking Management System ---");
	            System.out.println("1. Create Account");
	            System.out.println("2. Deposit");
	            System.out.println("3. Withdraw");
	            System.out.println("4. View Account");
	            System.out.println("5. Exit");
	            System.out.print("Enter choice: ");
	            choice = sc.nextInt();

	            switch (choice) {
	                case 1 -> createAccount();
	                case 2 -> deposit();
	                case 3 -> withdraw();
	                case 4 -> viewAccount();
	                case 5 -> System.out.println("Thank you!");
	                default -> System.out.println("Invalid choice!");
	            }
	        } while (choice != 5);
	    }

	    static void createAccount() {
	        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
	            System.out.print("Enter Account Number: ");
	            int accNo = sc.nextInt();
	            sc.nextLine();
	            System.out.print("Enter Name: ");
	            String name = sc.nextLine();
	            System.out.print("Enter Initial Balance: ");
	            double bal = sc.nextDouble();

	            Account acc = new Account(accNo, name, bal);
	            fw.write(acc.toString() + "\n");
	            System.out.println("Account created successfully!");
	        } catch (IOException e) {
	            System.out.println("Error creating account.");
	        }
	    }

	    static List<Account> loadAccounts() {
	        List<Account> list = new ArrayList<>();
	        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                list.add(Account.fromString(line));
	            }
	        } catch (IOException e) {
	            // file may not exist initially
	        }
	        return list;
	    }

	    static void saveAccounts(List<Account> list) {
	        try (FileWriter fw = new FileWriter(FILE_NAME)) {
	            for (Account acc : list) {
	                fw.write(acc.toString() + "\n");
	            }
	        } catch (IOException e) {
	            System.out.println("Error saving data.");
	        }
	    }

	    static void deposit() {
	        List<Account> list = loadAccounts();
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        for (Account acc : list) {
	            if (acc.getAccountNumber() == accNo) {
	                System.out.print("Enter amount: ");
	                double amt = sc.nextDouble();
	                acc.deposit(amt);
	                saveAccounts(list);
	                System.out.println("Deposit successful!");
	                return;
	            }
	        }
	        System.out.println("Account not found!");
	    }

	    static void withdraw() {
	        List<Account> list = loadAccounts();
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        for (Account acc : list) {
	            if (acc.getAccountNumber() == accNo) {
	                System.out.print("Enter amount: ");
	                double amt = sc.nextDouble();
	                if (acc.withdraw(amt)) {
	                    saveAccounts(list);
	                    System.out.println("Withdrawal successful!");
	                } else {
	                    System.out.println("Insufficient balance!");
	                }
	                return;
	            }
	        }
	        System.out.println("Account not found!");
	    }

	    static void viewAccount() {
	        List<Account> list = loadAccounts();
	        System.out.print("Enter Account Number: ");
	        int accNo = sc.nextInt();

	        for (Account acc : list) {
	            if (acc.getAccountNumber() == accNo) {
	                System.out.println("Account No: " + acc.getAccountNumber());
	                System.out.println("Name: " + acc.getName());
	                System.out.println("Balance: " + acc.getBalance());
	                return;
	            }
	        }
	        System.out.println("Account not found!");
	    }
	}


