package com.revature.controllers;

import com.revature.daos.BankAccountDAO;
import com.revature.models.BankAccount;
import com.revature.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {

    private BankAccountDAO bankAccountDAO;
    private BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountDAO bankAccountDAO, BankAccountService bankAccountService) {
        this.bankAccountDAO = bankAccountDAO;
        this.bankAccountService = bankAccountService;
    }

    //get all BankAccounts
    @GetMapping
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        List<BankAccount> bankAccounts = bankAccountDAO.findAll();
        return ResponseEntity.ok(bankAccounts);
    }

    //get BankAccount by Id
    @GetMapping("/{accountId}")
    public ResponseEntity<Object> getAccountById(@PathVariable int accountId){
        return bankAccountService.getById(accountId);
    }

    //get all accounts that belong to a certain user makes more sense in UserController

    // insert a new BankAccount
    @PostMapping("/{userId}")
    public ResponseEntity<String> addBankAccount(@RequestBody BankAccount bankAccount, @PathVariable int userId){
        return bankAccountService.addBankAccount(bankAccount, userId);
    }

    // Delete a BankAccount while NOT deleting related User
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteBankAccount(@PathVariable int accountId){
        return bankAccountService.deleteById(accountId);
    }

}
