package com.revature.services;

import com.revature.daos.BankAccountDAO;
import com.revature.models.BankAccount;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountService {

    private BankAccountDAO bankAccountDAO;

    @Autowired
    public BankAccountService(BankAccountDAO bankAccountDAO) {
        this.bankAccountDAO = bankAccountDAO;
    }

    // get BankAccount by Id
    public ResponseEntity<Object> getById(int accountId){
        Optional<BankAccount> bankAccount = bankAccountDAO.findById(accountId);
        if (bankAccount.isEmpty()){
            return ResponseEntity.badRequest().body("AccountId " + accountId " + does not exist");
        }
        return ResponseEntity.ok(bankAccount.get());
    }
    /* if statement can be replaced by
    return bankAccount.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body("AccountId " + accountId" + does not exist"));
     */

    // add bank account
    public ResponseEntity<String> addBankAccount(BankAccount bankAccount){
        try {
            BankAccount savedAccount = bankAccountDAO.save(bankAccount);
            String s = ("New BankAccount created with Id " + savedAccount.getAccountId() + "\n" + savedAccount);
            return ResponseEntity.status(201).body(s);
        } catch (ConstraintViolationException e){
            return ResponseEntity.badRequest().body("Validation Error: " + e.getMessage());
        } catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Database constraint violation: " + e.getMessage());
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Failed to add BankAccount");
        }
    }

    // delete bank account while keeping associated user
    public ResponseEntity<String> deleteById(int bankAccountId){
        Optional<BankAccount> bankAccount = bankAccountDAO.findById(bankAccountId);
        if(bankAccount.isEmpty()){
            return ResponseEntity.badRequest().body("No BankAccount found with Id: " + bankAccountId);
        }
        BankAccount bankAccount1 = bankAccount.get();
        bankAccountDAO.deleteById(bankAccountId);
        return ResponseEntity.accepted().body("BankAccount " + bankAccountId + " has been deleted");
    }
}
