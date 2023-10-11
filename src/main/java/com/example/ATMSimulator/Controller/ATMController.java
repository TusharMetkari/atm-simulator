package com.example.ATMSimulator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ATMSimulator.Service.ATMService;

@RestController
@RequestMapping("/api/atm")
public class ATMController {
	@Autowired
    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping("/balance/{accountNumber}")
    public double checkBalance(@PathVariable int accountNumber) {
        return atmService.checkBalance(accountNumber);
    }

    @PostMapping("/deposit/{accountNumber}")
    public boolean deposit(@PathVariable int accountNumber, @RequestParam double amount) {
        return atmService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw/{accountNumber}")
    public boolean withdraw(@PathVariable int accountNumber, @RequestParam double amount) {
        return atmService.withdraw(accountNumber, amount);
    }

    @PostMapping("/transfer/{fromAccount}/{toAccount}")
    public boolean transfer(
        @PathVariable int fromAccount, 
        @PathVariable int toAccount,
        @RequestParam double amount
    ) {
        return atmService.transfer(fromAccount, toAccount, amount);
    }
}

