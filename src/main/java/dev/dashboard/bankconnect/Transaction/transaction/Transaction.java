package dev.dashboard.bankconnect.Transaction.transaction;

import dev.dashboard.bankconnect.account.Account;
import jakarta.persistence.*;

import java.nio.DoubleBuffer;
import java.util.Date;

@MappedSuperclass
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column
    private String status = "PENDING";

    @Column
    private Date date = new Date();

    public Transaction() {

    }

    public Transaction(Double amount, Account account, String status, Date date) {
        this.amount = amount;
        this.account = account;
        this.status = status;
        this.date = date;
    }

    public Transaction(Long id, Double amount, Account account, String status, Date date) {
        this.id = id;
        this.amount = amount;
        this.account = account;
        this.status = status;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
