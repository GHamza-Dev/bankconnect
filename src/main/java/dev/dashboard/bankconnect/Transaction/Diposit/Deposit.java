package dev.dashboard.bankconnect.Transaction.Diposit;

import dev.dashboard.bankconnect.Transaction.Transaction;
import dev.dashboard.bankconnect.account.Account;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Deposit extends Transaction {
    public Deposit() {
    }

    public Deposit(Double amount, Account account, String status, Date date) {
        super(amount, account, status, date);
    }

    public Deposit(Long id, Double amount, Account account, String status, Date date) {
        super(id, amount, account, status, date);
    }
}
