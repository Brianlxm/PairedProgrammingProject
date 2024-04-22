package com.revature.models;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name ="bank_accounts")
@Component
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    private double accountBalance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "userId")
    private User user;

    //Boilerplate code -----------------

    //constructors
    public BankAccount() {
    }

    public BankAccount(int accountId, double accountBalance, User user) {
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.user = user;
    }

    //getters/setters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //toString
    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId=" + accountId +
                ", accountBalance=" + accountBalance +
                ", user=" + user +
                '}';
    }
}
