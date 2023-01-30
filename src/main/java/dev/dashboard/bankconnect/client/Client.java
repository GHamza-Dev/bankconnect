package dev.dashboard.bankconnect.client;


import dev.dashboard.bankconnect.account.Account;
import dev.dashboard.bankconnect.user.User;
import jakarta.persistence.*;

@Entity
@Table
public class Client extends User {

    @Column(name = "account_type")
    private String accountType = "standard";

    @OneToOne(mappedBy = "client")
    private Account account;

    private String status = "pending";

    public Client() {
    }

    public Client(Long id, String firstName, String lastName, String cin, String phone, String email, String password, boolean isActive, Account account) {
        super(id, firstName, lastName, cin, phone, email, password, isActive);
        this.account = account;
    }

    public Client(String firstName, String lastName, String cin, String phone, String email, String password, boolean isActive, Account account) {
        super(firstName, lastName, cin, phone, email, password, isActive);
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
