package dev.dashboard.bankconnect.account;

import dev.dashboard.bankconnect.client.Client;
import dev.dashboard.bankconnect.Transaction.transfer.Transfer;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {

    private static Long lastAccountNumber = 0l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "balance")
    private double balance;

    @OneToOne(optional = false)
    private Client client;

    @Column(name = "account_type")
    private String accountType;

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Transfer> sends;

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Transfer> receives;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber() {
//        System.out.println(String.format("%024d", ++lastAccountNumber));
        this.accountNumber = String.format("%024d", ++lastAccountNumber);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public List<Transfer> getSends() {
        return sends;
    }

    public void setSends(List<Transfer> sends) {
        this.sends = sends;
    }

    public List<Transfer> getReceives() {
        return receives;
    }

    public void setReceives(List<Transfer> receives) {
        this.receives = receives;
    }
}
