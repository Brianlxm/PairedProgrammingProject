package com.revature.services;

import com.revature.daos.BankAccountDAO;
import com.revature.daos.UserDAO;
import com.revature.models.BankAccount;
import com.revature.models.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserDAO userDAO;
    private BankAccountDAO bankAccountDAO;


    @Autowired
    public UserService(UserDAO userDAO, BankAccountDAO bankAccountDAO) {
        this.userDAO = userDAO;
        this.bankAccountDAO = bankAccountDAO;
    }


    // get user by id
    public ResponseEntity<Object> getUserById(int userId){
        User user = userDAO.findById(userId).orElse(null);
        if (user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().body("No User associated with Id " + userId);
    }

    //add new user
    public ResponseEntity<String> addNewUser(User newUser){
        try{
            userDAO.save(newUser);
            return ResponseEntity.ok("User created successfully\n" + newUser);
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("DataIntegrityViolationException: username/password/name required nullable=false, and username unique=true");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An unexpected error occurred while processing the request");
        }
    }

    //update user
    public ResponseEntity<String> updateUser(int userId, User updatedUser){
        if (userId != updatedUser.getUserId()){
            return ResponseEntity.badRequest().body("User id does not match");
        }
        try{
            User existingUser = userDAO.findById(updatedUser.getUserId()).orElse(null);
            if (existingUser == null){
                return ResponseEntity.badRequest().body("user_id=" + updatedUser.getUserId() + " does not exist");
            }
            existingUser.setName(updatedUser.getName());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setUsername(updatedUser.getUsername());
            userDAO.save(existingUser);
            return ResponseEntity.ok("User updated successfully\n" + updatedUser);
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("User update failed due to data constraint violations");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("An unexpected error occurred while processing the request");
        }
    }

    //get all BankAccounts that belong to a certain userId
    public ResponseEntity<List<BankAccount>> getAccountsByUserId(int userId){
            User user = userDAO.findById(userId).orElse(null);
            List<BankAccount> bankAccounts = bankAccountDAO.findByUserUserId(userId);
            return ResponseEntity.ok(bankAccounts);
    }
}
