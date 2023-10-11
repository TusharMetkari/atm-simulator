package com.example.ATMSimulator.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.ATMSimulator.Entity.Account;

@Service
public class ATMService {
    private Map<Integer, Account> accounts;

    public ATMService() {
        accounts = new HashMap<>();
        // Initialize accounts (you can add more)
        accounts.put(12345, new Account(12345, 1000.0));
        accounts.put(67890, new Account(67890, 1500.0));
    }

    public double checkBalance(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            return accounts.get(accountNumber).getBalance();
        }
        return -1.0; // Account not found
    }

    public boolean deposit(int accountNumber, double amount) {
        if (accounts.containsKey(accountNumber) && amount > 0) {
            Account account = accounts.get(accountNumber);
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false; // Account not found or invalid amount
    }

    public boolean withdraw(int accountNumber, double amount) {
        if (accounts.containsKey(accountNumber) && amount > 0) {
            Account account = accounts.get(accountNumber);
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                return true;
            }
        }
        return false; // Account not found, insufficient balance, or invalid amount
    }

    public boolean transfer(int fromAccountNumber, int toAccountNumber, double amount) {
        if (accounts.containsKey(fromAccountNumber) && accounts.containsKey(toAccountNumber) && amount > 0) {
            Account fromAccount = accounts.get(fromAccountNumber);
            Account toAccount = accounts.get(toAccountNumber);
            if (fromAccount.getBalance() >= amount) {
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                return true;
            }
        }
        return false; // One or both accounts not found, insufficient balance, or invalid amount
    }
}

